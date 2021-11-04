package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoContato;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {
    @ApiModelProperty(value = "Id da pessoa")
    private Integer idPessoa;
    @NotNull
    @ApiModelProperty(value = "Tipo de contato")
    private TipoContato tipoContato;
    @NotEmpty
    @NotNull
    @Size(max = 13)
    @ApiModelProperty(value = "Número de Telefone.")
    private String numero;
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Descrição")
    private String descricao;
}
