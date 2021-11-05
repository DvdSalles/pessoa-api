package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDTO;
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
        PessoaEntity pessoaCriada = pessoaRepository.create(pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDTO.class);
        dadosPessoaisClient.createDadosPessoais(pessoaDTO.getDadosPessoaisDTO());
        pessoaDTO.setDadosPessoaisDTO(dadosPessoaisClient.getPorCpf(pessoaEntity.getCpf()));
//        emailService.enviarEmailComTemplate(pessoaDTO);
        return pessoaDTO;
    }

    public List<PessoaDTO> list(){
        return pessoaRepository.list().stream()
                .map(pessoa -> {
                   PessoaDTO dto = objectMapper.convertValue(pessoa, PessoaDTO.class);
                   dto.setDadosPessoaisDTO(dadosPessoaisClient.getPorCpf(pessoa.getCpf()));
                   return dto;
                })
                .collect(Collectors.toList());
    }

    public PessoaDTO getById(Integer id) throws RegraDeNegocioException {
        PessoaEntity entity = pessoaRepository.buscarPorId(id);
        DadosPessoaisDTO dadosPessoaisDTO = dadosPessoaisClient.getPorCpf(entity.getCpf());
        PessoaDTO dto = objectMapper.convertValue(entity, PessoaDTO.class);
        dto.setDadosPessoaisDTO(dadosPessoaisDTO);
        return dto;
    }

    public PessoaDTO update(Integer id,
                               PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity pessoaAtualizada = pessoaRepository.update(id, pessoaEntity);
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaAtualizada, PessoaDTO.class);
        pessoaDTO.setDadosPessoaisDTO(dadosPessoaisClient.getPorCpf(pessoaEntity.getCpf()));
//        emailService.enviarEmailComTemplateUpdate(pessoaDTO);
        return pessoaDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        PessoaEntity pessoaDeletada = pessoaRepository.buscarPorId(id);
        dadosPessoaisClient.delete(pessoaDeletada.getCpf());
        pessoaRepository.delete(id);
//        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaDeletada, PessoaDTO.class);
//        emailService.enviarEmailComTemplateDelete(pessoaDTO);
    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .map(pessoa -> {
                    PessoaDTO dto = objectMapper.convertValue(pessoa, PessoaDTO.class);
                    dto.setDadosPessoaisDTO(dadosPessoaisClient.getPorCpf(pessoa.getCpf()));
                    return dto;
                })
                .collect(Collectors.toList());

    }
}
