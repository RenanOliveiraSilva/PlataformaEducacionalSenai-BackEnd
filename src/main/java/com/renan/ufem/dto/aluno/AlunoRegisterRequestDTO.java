package com.renan.ufem.dto.aluno;

import java.util.Date;

public record AlunoRegisterRequestDTO(
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
