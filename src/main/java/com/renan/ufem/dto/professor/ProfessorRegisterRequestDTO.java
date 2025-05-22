package com.renan.ufem.dto.professor;

import com.renan.ufem.enums.SituacaoType;

import java.util.Date;

public record ProfessorRegisterRequestDTO(
        String nome,
        String CPF,
        SituacaoType situacao,
        String logradouro,
        String bairro,
        int numero,
        String cidade,
        String UF,
        String email,
        String senha,
        String telefone,
        String matricula,
        String sexo,
        Date data_nasc,
        String id_secretatia
) {
}
