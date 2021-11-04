package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoDTO extends EnderecoCreateDTO {
    @ApiModelProperty(value = "Id do endereço")
    private Integer idEndereco;
}
