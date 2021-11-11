package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/endereco")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final EnderecoRepository enderecoRepository;

    @ApiOperation(value = "Fornece uma lista com todos os endereços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping
    public List<EnderecoDTO> list() {
        return enderecoService.list();
    }


//    @ApiOperation(value = "Fornece uma lista de endereços correspondente à pessoa do id inserido.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
//            @ApiResponse(code = 400, message = "Pessoa não encontrada."),
//            @ApiResponse(code = 500, message = "Problema interno no sistema."),
//    })
//    @GetMapping("/{idPessoa}/pessoa")
//    public List<EnderecoDTO> listEnderecoPorPessoa(@PathVariable("idPessoa")Integer idPessoa) {
//        return enderecoService.listEnderecoPorPessoa(idPessoa);
//    }


    @ApiOperation(value = "Busca um endereço pelo id de endereço.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Endereço não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping("/{idEndereco}")
    public EnderecoDTO listEnderecoPorId(@PathVariable("idEndereco") Integer idEndereco) throws RegraDeNegocioException {
        return enderecoService.getById(idEndereco);
    }


    @ApiOperation(value = "Cria um novo endereço e insere na pessoa do id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inseridos incorretamente ou pessoa não encontrada."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PostMapping
    public EnderecoDTO create(@RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("Criando endereço.");
        EnderecoDTO enderecoDTO1 = enderecoService.create(enderecoCreateDTO);
        log.info("Endereço criado com sucesso.");
        return enderecoDTO1;
    }


    @ApiOperation(value = "Atualiza o endereço correspondente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Endereço não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PutMapping("/{id}")
    public EnderecoDTO update(@PathVariable("id") Integer idEndereco,
                                 @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("Atualizando endereço.");
        EnderecoDTO enderecoDTO1 = enderecoService.update(idEndereco, enderecoCreateDTO);
        log.info("Endereço atualizado com sucesso.");
        return enderecoDTO1;
    }


    @ApiOperation(value = "Deleta o endereço correspondente ao id inserido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Endereço não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idEndereco) throws RegraDeNegocioException {
        log.info("Deletando endereço");
        enderecoService.delete(idEndereco);
        log.info("Endereço deletado com sucesso.");
    }

    @GetMapping("/endereco-por-pais")
    public List<EnderecoEntity> listarEnderecosPorPais(@RequestParam String pais) {
        return enderecoRepository.procurarPorPais(pais);
    }

    @GetMapping("/endereco-por-idpesssoa")
    public List<EnderecoEntity> listarEnderecoPorIdPessoa(@RequestParam Integer idPessoa) {
        return enderecoRepository.procurarPorIdPessoa(idPessoa);
    }

    @GetMapping("/endereco-cidade-ou-pais")
    public List<EnderecoEntity> enderecoPorCidadeOuPais(@RequestParam(value = "cidade", required = false) String cidade,
                                                        @RequestParam(value = "pais", required = false) String pais) {
        return enderecoRepository.enderecoPorCidadeOuPais(cidade, pais);
    }

    @GetMapping("/endereco-sem-complemento")
    public List<EnderecoEntity> enderecoSemComplemento(){
        return enderecoRepository.enderecoSemComplemento();
    }
}
