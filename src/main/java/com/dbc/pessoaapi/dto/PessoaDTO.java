package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaDTO extends PessoaCreateDTO{
    @ApiModelProperty(value = "Id da pessoa")
    private Integer idPessoa;
}
