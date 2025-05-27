package com.renan.ufem.dto.aluno;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.renan.ufem.enums.SituacaoType;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlunoUpdateDTO(
        String nome,
        String CPF,
        String logradouro,
        String bairro,
        Integer numero,
        String cidade,
        String UF,
        @Email(message = "Email deve estar em um formato válido")
        String email,
        String senha,
        String telefone,
        String matricula,
        String sexo,
        LocalDate data_nasc,
        SituacaoType situacao,
        String idMatricula
) {
}
