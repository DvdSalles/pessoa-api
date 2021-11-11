package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.*;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final DadosPessoaisClient dadosPessoaisClient;


    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity pessoaCriada = pessoaRepository.save(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDTO.class);
//        emailService.enviarEmailComTemplate(pessoaDTO);
        return pessoaDTO;
    }

    private PessoaEntity findById(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada."));
        return pessoaEntity;
    }

    public List<PessoaDTO> list(){
        return pessoaRepository.findAll().stream()
                .map(pessoa -> {
                   PessoaDTO dto = objectMapper.convertValue(pessoa, PessoaDTO.class);
                   return dto;
                })
                .collect(Collectors.toList());
    }

    public PessoaDTO getById(Integer id) throws RegraDeNegocioException {
        PessoaEntity entity = findById(id);
        PessoaDTO dto = objectMapper.convertValue(entity, PessoaDTO.class);
        return dto;
    }

    public PessoaDTO update(Integer id,
                               PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        pessoaEntity.setIdPessoa(id);
        PessoaEntity pessoaAtualizada = pessoaRepository.save(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaAtualizada, PessoaDTO.class);
//        emailService.enviarEmailComTemplateUpdate(pessoaDTO);
        return pessoaDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        PessoaEntity pessoaDeletada = findById(id);
        pessoaRepository.delete(pessoaDeletada);
//        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaDeletada, PessoaDTO.class);
//        emailService.enviarEmailComTemplateDelete(pessoaDTO);
    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.findAll().stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .map(pessoa -> {
                    PessoaDTO dto = objectMapper.convertValue(pessoa, PessoaDTO.class);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<PessoaComContatoDTO> listarPessoaComContatoComOuSemId(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            List<PessoaComContatoDTO> lista = new ArrayList<>();
            PessoaEntity pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada."));
            PessoaComContatoDTO dto = objectMapper.convertValue(pessoa, PessoaComContatoDTO.class);
            dto.setContatos(pessoa.getContatos().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList()));
            lista.add(dto);
            return lista;
        } else {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaComContatoDTO dto = objectMapper.convertValue(pessoaEntity, PessoaComContatoDTO.class);
                        dto.setContatos(pessoaEntity.getContatos().stream()
                                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                                .collect(Collectors.toList()));
                        return dto;

                    })
                    .collect(Collectors.toList());
        }
    }

    public List<PessoaComEnderecoDTO> listarPessoaComEnderecoComOuSemId(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            List<PessoaComEnderecoDTO> lista = new ArrayList<>();
            PessoaEntity pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada."));
            PessoaComEnderecoDTO dto = objectMapper.convertValue(pessoa, PessoaComEnderecoDTO.class);
            dto.setEnderecos(pessoa.getEnderecos().stream()
                    .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                    .collect(Collectors.toList()));
            lista.add(dto);
            return lista;
        } else {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaComEnderecoDTO dto = objectMapper.convertValue(pessoaEntity, PessoaComEnderecoDTO.class);
                        dto.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                                .collect(Collectors.toList()));
                        return dto;

                    })
                    .collect(Collectors.toList());
        }
    }
    public List<PessoaContatoEnderecoDTO> listarPessoaComContatoEnderecoComOuSemId(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            List<PessoaContatoEnderecoDTO> lista = new ArrayList<>();
            PessoaEntity pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada."));
            PessoaContatoEnderecoDTO dto = objectMapper.convertValue(pessoa, PessoaContatoEnderecoDTO.class);
            dto.setEnderecos(pessoa.getEnderecos().stream()
                    .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                    .collect(Collectors.toList()));
            dto.setContatos(pessoa.getContatos().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList()));
            lista.add(dto);
            return lista;
        } else {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaContatoEnderecoDTO dto = objectMapper.convertValue(pessoaEntity, PessoaContatoEnderecoDTO.class);
                        dto.setContatos(pessoaEntity.getContatos().stream()
                                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                                .collect(Collectors.toList()));
                        dto.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                                .collect(Collectors.toList()));
                        return dto;
                    })
                    .collect(Collectors.toList());
        }
    }
}
