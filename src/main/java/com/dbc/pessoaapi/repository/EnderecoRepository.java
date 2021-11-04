package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.entity.TipoEndereco;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<EnderecoEntity> listaEnderecoEntity = new ArrayList<>();
    private AtomicInteger COUNTER_ENDERECO = new AtomicInteger();

    public EnderecoRepository () {
        listaEnderecoEntity.add(new EnderecoEntity(COUNTER_ENDERECO.incrementAndGet(), 2, TipoEndereco.RESIDENCIAL, "Rua das Ruas",
                55, "Casa", "72887998", "Lagoinha", "DF", "Brasil"));
    }

    public List<EnderecoEntity> list() {
        return listaEnderecoEntity;
    }

    public List<EnderecoEntity> listEnderecoPorPessoa(Integer idPessoa) {
        return listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public EnderecoEntity listEnderecoPorId(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntityRecuperado = listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        return enderecoEntityRecuperado;
    }

    public EnderecoEntity create(EnderecoEntity enderecoEntity) {
        enderecoEntity.setIdEndereco(COUNTER_ENDERECO.incrementAndGet());
        listaEnderecoEntity.add(enderecoEntity);
        return enderecoEntity;
    }

    public EnderecoEntity update(Integer id, EnderecoEntity enderecoEntityAtualizar) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntityRecuperado = listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        enderecoEntityRecuperado.setTipo(enderecoEntityAtualizar.getTipo());
        enderecoEntityRecuperado.setLogradouro(enderecoEntityAtualizar.getLogradouro());
        enderecoEntityRecuperado.setNumero(enderecoEntityAtualizar.getNumero());
        enderecoEntityRecuperado.setComplemento(enderecoEntityAtualizar.getComplemento());
        enderecoEntityRecuperado.setCep(enderecoEntityAtualizar.getCep());
        enderecoEntityRecuperado.setCidade(enderecoEntityAtualizar.getCidade());
        enderecoEntityRecuperado.setEstado(enderecoEntityAtualizar.getEstado());
        enderecoEntityRecuperado.setPais(enderecoEntityAtualizar.getPais());
        return enderecoEntityRecuperado;
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntityRecuperado = listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        listaEnderecoEntity.remove(enderecoEntityRecuperado);
    }
}
