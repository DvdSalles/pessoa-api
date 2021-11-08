package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    private EnderecoEntity findById(Integer id) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado."));
        return  enderecoEntity;
    }

    public List<EnderecoDTO> list() {
        return enderecoRepository.findAll().stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

//    public List<EnderecoDTO> listEnderecoPorPessoa(Integer idPessoa) {
//        return enderecoRepository.listEnderecoPorPessoa(idPessoa).stream()
//                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
//                .collect(Collectors.toList());
//    }

    public EnderecoDTO getById(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = findById(idEndereco);
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);

    }

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
//        pessoaRepository.getById(idPessoa);
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
//        enderecoEntity.setIdPessoa(idPessoa);
        EnderecoEntity enderecoCriado = enderecoRepository.save(enderecoEntity);
        return objectMapper.convertValue(enderecoCriado, EnderecoDTO.class);
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
//        pessoaRepository.buscarPorId(enderecoAtualizar.getIdPessoa());
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        enderecoEntity.setIdEndereco(idEndereco);
        EnderecoEntity enderecoAtualizar = enderecoRepository.save(enderecoEntity);
        return objectMapper.convertValue(enderecoAtualizar, EnderecoDTO.class);
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity endereco = findById(idEndereco);
        enderecoRepository.delete(endereco);
    }
}
