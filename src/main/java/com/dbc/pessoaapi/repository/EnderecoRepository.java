package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.entity.TipoEndereco;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
    @Query("select e from ENDERECO_PESSOA e where upper(pais) = upper(:pais)")
    Page<EnderecoEntity> procurarPorPaisPaginado(String pais, Pageable pageable);

    @Query("select e from ENDERECO_PESSOA e join e.pessoas p where p.idPessoa = :idPessoa")
    List<EnderecoEntity> procurarPorIdPessoa(Integer idPessoa);

    @Query(value = "SELECT * FROM VEM_SER.ENDERECO_PESSOA e WHERE upper(CIDADE) = upper(:cidade) OR upper(PAIS) = upper(:pais)", nativeQuery = true)
    List<EnderecoEntity> enderecoPorCidadeOuPais(String cidade, String pais);

    @Query(value = "SELECT * FROM VEM_SER.ENDERECO_PESSOA e WHERE complemento is null", nativeQuery = true)
    List<EnderecoEntity> enderecoSemComplemento();

    @Query("select e from ENDERECO_PESSOA e where upper(pais) = upper(:pais)")
    List<EnderecoEntity> procurarPorPais(String pais);

}
