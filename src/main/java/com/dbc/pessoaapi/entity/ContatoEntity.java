package com.dbc.pessoaapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "CONTATO")
public class ContatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "seq_contato", allocationSize = 1)
    @Column(name = "id_contato")
    private Integer idContato;
    @Column(name = "id_pessoa")
    private Integer idPessoa;
    @Enumerated
    @Column(name = "tipo")
    private TipoContato tipoContato;
    @Column(name = "numero")
    private String numero;
    @Column(name = "descricao")
    private String descricao;
}
