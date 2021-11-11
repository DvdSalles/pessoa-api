package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paginação")
@RequiredArgsConstructor
public class PaginacaoController {
    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;


    @GetMapping("/contatos-ordenados-por-descrição")
    public Page<ContatoEntity> contatosPorOrdemDescricao(
            @RequestParam Integer pagina,
            @RequestParam Integer numeroDeRegistros
    ) {
        Pageable pageable = PageRequest.of(pagina, numeroDeRegistros, Sort.by("descricao"));
        Page<ContatoEntity> paginaDeContatos = contatoRepository.findAll(pageable);
        return paginaDeContatos;
    }

    @GetMapping("/endereços-ordenados-pelo-cep")
    public Page<EnderecoEntity> enderecoOrdenadosPorCep(
            @RequestParam Integer pagina,
            @RequestParam Integer numeroDeRegistros
    ) {
        Pageable pageable = PageRequest.of(pagina, numeroDeRegistros, Sort.by("cep"));
        Page<EnderecoEntity> paginaDeEnderecos = enderecoRepository.findAll(pageable);
        return paginaDeEnderecos;
    }

    @GetMapping("/endereços-filtrado-por-pais")
    public Page<EnderecoEntity> enderecoOrdenadosPorCep(
            @RequestParam Integer pagina,
            @RequestParam Integer numeroDeRegistros,
            @RequestParam String pais
    ) {
        Pageable pageable = PageRequest.of(pagina, numeroDeRegistros);
        Page<EnderecoEntity> paginaDeEnderecos = enderecoRepository.procurarPorPaisPaginado(pais, pageable);
        return paginaDeEnderecos;
    }
}
