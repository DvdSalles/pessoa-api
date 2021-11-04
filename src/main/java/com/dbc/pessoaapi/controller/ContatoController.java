package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.ContatoCreateDTO;
import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.service.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ContatoController {
    private final ContatoService contatoService;

    @ApiOperation(value = "Cria um contato na pessoa referente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inseridos incorretamente ou pessoa não encontrada."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PostMapping("/{idPessoa}")
    public ContatoDTO create(@PathVariable("idPessoa") Integer idPessoa,
                             @RequestBody @Valid ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        log.info("Criando contato.");
        ContatoDTO contatoDTO1 = contatoService.create(idPessoa, contatoCreateDTO);
        log.info("Contato criado com sucesso.");
        return contatoDTO1;
    }


    @ApiOperation(value = "Fornece uma lista com todos os contatos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping
    public List<ContatoDTO> list() {
        return contatoService.list();
    }


    @ApiOperation(value = "Fornece uma lista dos contatos referentes à pessoa do id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Pessoa não encontrada"),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping("/{idPessoa}")
    public List<ContatoDTO> listPorIdPessoa(@PathVariable("idPessoa") Integer idPessoa) {
        return contatoService.listPorIdPessoa(idPessoa);
    }


    @ApiOperation(value = "Atualiza o contato correspondente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inseridos incorretamente ou contato não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PutMapping("/{idContato}")
    public ContatoDTO update(@PathVariable("idContato") Integer idContato,
                                @RequestBody @Valid ContatoCreateDTO contatoCreateDTOAtualizar) throws RegraDeNegocioException {
        log.info("Atualizando contato.");
        ContatoDTO contatoDTO1 = contatoService.update(idContato, contatoCreateDTOAtualizar);
        log.info("Contato atualizado com sucesso.");
        return contatoDTO1;
    }


    @ApiOperation(value = "Deleta o contato correspondente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Contato não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato") Integer idContato) throws RegraDeNegocioException {
        log.info("Deletando contato.");
        contatoService.delete(idContato);
        log.info("Contato deletado com sucesso.");
    }


}
