package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Nome da pessoa")
    private String nome;

    @NotNull
    @Past
    @ApiModelProperty(value = "Data de nascimento")
    private LocalDate dataNascimento;

    @Size(max = 11, min=11, message = "CPF deve conter 11 caracteres")
    @NotNull
    @ApiModelProperty(value = "CPF")
    private String cpf;

    @Email
    @NotNull
    @ApiModelProperty(value = "E-mail")
    private String email;

    private DadosPessoaisDTO dadosPessoaisDTO;
}
