package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {
//    @ApiModelProperty(value = "Id da pessoa")
//    private Integer idPessoa;
    @NotNull
    @ApiModelProperty(value = "Tipo de endereço")
    private TipoEndereco tipo;
    @NotEmpty
    @Size(max = 250)
    @ApiModelProperty(value = "Nome do logradouro")
    private String logradouro;
    @NotNull
    @ApiModelProperty(value = "Número")
    private Integer numero;
    @ApiModelProperty(value = "Complemento")
    private String complemento;
    @NotEmpty
    @NotNull
    @Size(max = 8, min = 8, message = "CEP vazio ou não contém 8 caracteres.")
    @ApiModelProperty(value = "CPF")
    private String cep;
    @NotNull
    @NotEmpty
    @Size(max = 250)
    @ApiModelProperty(value = "Cidade")
    private String cidade;
    @NotEmpty
    @NotNull
    @Size(max = 2, min = 2, message = "Estado deve ser inserido no formato de sigla, apenas 2 caracteres.")
    @ApiModelProperty(value = "Estado")
    private String estado;
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "País")
    private String pais;
}
