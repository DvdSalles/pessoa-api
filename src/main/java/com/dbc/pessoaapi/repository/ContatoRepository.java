package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.TipoContato;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Integer> {
    @Query("select c from CONTATO c where c.tipoContato = :tipo")
    List<ContatoEntity> contatosPorTipoContato(TipoContato tipo);

    @Query(value = "SELECT * FROM VEM_SER.CONTATO c WHERE ID_PESSOA = :idPessoa", nativeQuery = true)
    List<ContatoEntity> contatosPorIdPessoa(Integer idPessoa);

}
