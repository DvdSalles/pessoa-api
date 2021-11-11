package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity,Integer> {

    List<PessoaEntity> findByNomeContainsIgnoreCase(String nome);
    PessoaEntity findByCpf(String cpf);
    List<PessoaEntity> findByDataNascimentoBetween (LocalDate datainicial, LocalDate datafinal);

    @Query("select p from PESSOA p where p.dataNascimento between :inicio and :fim")
    List<PessoaEntity> buscarEntreDatasDeNascimento(LocalDate inicio, LocalDate fim);

    @Query("select p from PESSOA p join p.enderecos e")
    List<PessoaEntity> buscarPessoasComEnderecos();

    @Query(value = "SELECT * FROM VEM_SER.PESSOA p LEFT JOIN VEM_SER.PESSOA_X_PESSOA_ENDERECO pe ON (p.ID_PESSOA = pe.ID_PESSOA) " +
            " WHERE pe.ID_PESSOA IS NULL", nativeQuery = true)
    List<PessoaEntity> buscarPessoasComEnderecoNull();


}
