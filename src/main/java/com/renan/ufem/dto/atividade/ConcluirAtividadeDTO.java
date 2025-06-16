package com.renan.ufem.dto.atividade;

import java.time.LocalDate;

public record ConcluirAtividadeDTO(
        Float nota,
        String status,         // ex: "CONCLUIDA"
        LocalDate dataEntrega  // data em que o aluno entregou
) {}
