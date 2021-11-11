package com.dbc.pessoaapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaComEnderecoDTO extends PessoaDTO{

    private List<EnderecoDTO> enderecos;
}
