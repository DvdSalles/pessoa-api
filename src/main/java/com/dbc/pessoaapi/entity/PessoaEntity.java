package com.dbc.pessoaapi.entity;

import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "PESSOA")
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
    @SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "seq_pessoa2", allocationSize = 1)
    @Column(name = "id_pessoa")
    private Integer idPessoa;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;
}