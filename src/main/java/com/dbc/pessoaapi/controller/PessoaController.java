package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.*;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.dbc.pessoaapi.service.PessoaService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PessoaController {
    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;


//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello world!";
//    }

    @ApiOperation(value = "Cria uma nova pessoa.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inseridos incorretamente."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PostMapping
    public PessoaDTO create(@RequestBody @Valid PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        log.info("Criando pessoa.");
        PessoaDTO pessoaDTO1 = pessoaService.create(pessoaCreateDTO);
        log.info("Pessoa criada com sucesso.");
        return pessoaDTO1;
    }


    @ApiOperation(value = "Mostra a lista com todas as pessoas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping
    public List<PessoaDTO> list() {
        return pessoaService.list();
    }

    @GetMapping("/{idPessoa}")
    public PessoaDTO buscarPorId(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
       return pessoaService.getById(id);
    }


    @ApiOperation(value = "Faz uma busca de pessoa pelo nome.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping("/byname")
    public List<PessoaDTO> listByName(@RequestParam("nome") String nome) {
        return pessoaService.listByName(nome);
    }


    @ApiOperation(value = "Atualiza a pessoa correspondente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inseridos incorretamente."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PutMapping("/{idPessoa}")
    public PessoaDTO update(@PathVariable("idPessoa") Integer id,
                               @RequestBody @Valid PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        log.info("Atualizando pessoa.");
        PessoaDTO pessoaDTO1 = pessoaService.update(id, pessoaCreateDTO);
        log.info("Pessoa atualizada com sucesso.");
        return pessoaDTO1;
    }


    @ApiOperation(value = "Deleta uma pessoa cadastrada através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Pessoa não encontrada."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa") Integer id) throws Exception {
        log.info("Deletando pessoa.");
        pessoaService.delete(id);
        log.info("Pessoa deletada com sucesso.");
    }

    @GetMapping("/buscar-nome")
    public List<PessoaEntity> buscarNome(@RequestParam("nome") String nome) {
       return pessoaRepository.findByNomeContainsIgnoreCase(nome);
    }

    @GetMapping("/buscar-cpf")
    public PessoaEntity buscarCpf(@RequestParam("cpf") String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @GetMapping("/buscar-entre-data")
    public List<PessoaEntity> buscarEntreDatas(@RequestParam("incial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate incial,
                                               @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return pessoaRepository.findByDataNascimentoBetween(incial, fim);
    }

    @GetMapping("/buscar-pessoa+contato")
    public List<PessoaComContatoDTO> listarPessoaComContatoComOuSemId(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listarPessoaComContatoComOuSemId(idPessoa);
    }

    @GetMapping("/buscar-pessoa+endereco")
    public List<PessoaComEnderecoDTO> listarPessoaComEnderecoComOuSemId(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listarPessoaComEnderecoComOuSemId(idPessoa);
    }

    @GetMapping("/buscar-pessoa+contato+endereco")
    public List<PessoaContatoEnderecoDTO> listarPessoaComContatoEnderecoComOuSemId(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listarPessoaComContatoEnderecoComOuSemId(idPessoa);
    }

    @GetMapping("/buscarEntreDatasDeNascimento")
    public List<PessoaEntity> buscarNascimentoEntreDatas(@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
                                                         @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return pessoaRepository.buscarEntreDatasDeNascimento(inicio, fim);
    }

    @GetMapping("/buscarPessoasComEndereco")
    public List<PessoaEntity> buscarPessoasComEndereco(){
        return pessoaRepository.buscarPessoasComEnderecos();
    }

    @GetMapping("/buscar-pessoas-endereco-null")
    public List<PessoaEntity> buscarPessoasComEnderecoNull() {
        return pessoaRepository.buscarPessoasComEnderecoNull();
    }
}
