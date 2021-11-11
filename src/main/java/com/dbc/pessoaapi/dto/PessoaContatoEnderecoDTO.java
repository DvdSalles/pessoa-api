package com.dbc.pessoaapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaContatoEnderecoDTO extends PessoaDTO {
    private List<ContatoDTO> contatos;
    private List<EnderecoDTO> enderecos;
}
