package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaComContatoDTO extends PessoaDTO {

    private List<ContatoDTO> contatos;
}
