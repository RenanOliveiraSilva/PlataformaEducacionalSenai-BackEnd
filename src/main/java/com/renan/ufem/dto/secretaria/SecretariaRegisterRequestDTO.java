package com.renan.ufem.dto.secretaria;

public record SecretariaRegisterRequestDTO(
        String nome,
        String logradouro,
        String bairro,
        int numero,
        String cidade,
        String UF,
        String email,
        String senha,
        String telefone
) {
}
