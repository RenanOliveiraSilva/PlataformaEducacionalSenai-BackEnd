package com.renan.ufem.dto.professor;

import java.util.Date;

public record ProfessorRegisterRequestDTO(
        String nome,
        String CPF,
        String situacao,
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
        Date data_nasc
) {
}
