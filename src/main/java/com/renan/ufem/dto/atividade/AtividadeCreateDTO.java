package com.renan.ufem.dto.atividade;

import java.time.LocalDate;

public record AtividadeCreateDTO(
        String nome,
        String descricao,
        LocalDate dataEntrega,
        Float peso
) {}

