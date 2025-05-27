package com.renan.ufem.dto.secretaria;

import com.renan.ufem.domain.Secretaria;

public record SecretariaDTO(
        String nome,
        String logradouro,
        String bairro,
        Integer numero,
        String cidade,
        String UF,
        String email,
        String senha,
        String telefone
) {
    public SecretariaDTO(Secretaria secretaria) {
        this(
                secretaria.getNome(),
                secretaria.getLogradouro(),
                secretaria.getBairro(),
                secretaria.getNumero(),
                secretaria.getCidade(),
                secretaria.getUF(),
                secretaria.getEmail(),
                null, // NUNCA retornar senha!
                secretaria.getTelefone()
        );
    }
}
