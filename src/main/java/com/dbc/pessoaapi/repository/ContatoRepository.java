package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.TipoContato;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {
    private static List<ContatoEntity> listaContatoEntities = new ArrayList<>();
    private AtomicInteger COUNTER_CONTATO = new AtomicInteger();

    public ContatoRepository() {
        listaContatoEntities.add(new ContatoEntity(COUNTER_CONTATO.incrementAndGet(), 2, TipoContato.COMERCIAL, "999555599", "Ligue já!"));
    }

    public ContatoEntity create(ContatoEntity contatoEntity) {

        contatoEntity.setIdContato(COUNTER_CONTATO.incrementAndGet());
        listaContatoEntities.add(contatoEntity);
        return contatoEntity;
    }

    public List<ContatoEntity> list() {
        return listaContatoEntities;
    }

    public List<ContatoEntity> listPorIdPessoa(Integer idPessoa) {
        return listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());

    }

    public ContatoEntity update(Integer id, ContatoEntity contatoEntityAtualizar) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado."));
        contatoEntityRecuperado.setTipoContato(contatoEntityAtualizar.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoEntityAtualizar.getNumero());
        contatoEntityRecuperado.setDescricao(contatoEntityAtualizar.getDescricao());
        return contatoEntityRecuperado;
    }

    public void delete(Integer idContato) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(idContato))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado."));
        listaContatoEntities.remove(contatoEntityRecuperado);
    }
}
