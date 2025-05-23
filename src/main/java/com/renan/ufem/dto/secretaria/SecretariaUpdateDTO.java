package com.renan.ufem.dto.secretaria;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SecretariaUpdateDTO(
        String nome,
        String email,
        String senha,
        String telefone,
        String UF,
        String cidade,
        String bairro,
        String logradouro,
        Integer numero
) {}
