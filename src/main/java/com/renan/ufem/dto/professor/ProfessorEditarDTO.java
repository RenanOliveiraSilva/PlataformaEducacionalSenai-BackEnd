package com.renan.ufem.dto.professor;

import com.renan.ufem.enums.SituacaoType;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProfessorEditarDTO(
    String nome,
    String CPF,
    SituacaoType situacao,
    String logradouro,
    String bairro,
    Integer numero,
    String cidade,
    String UF,
    String email,
    String senha,
    String telefone,
    String sexo,
    LocalDate data_nasc,
    String id_secretaria
){}
