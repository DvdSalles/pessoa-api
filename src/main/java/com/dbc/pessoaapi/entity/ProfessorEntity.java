package com.dbc.pessoaapi.entity;

import com.dbc.pessoaapi.entity.PK.ProfessorPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PROFESSOR")
public class ProfessorEntity {
    @EmbeddedId
    private ProfessorPK professorPK;
    @Column(name = "nome")
    private String nome;
    @Column(name = "salario")
    private Double salario;
}
