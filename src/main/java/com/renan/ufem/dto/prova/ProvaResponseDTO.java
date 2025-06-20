package com.renan.ufem.dto.prova;

import com.renan.ufem.enums.AtividadeStatus;

import java.time.LocalDate;

public record ProvaResponseDTO(
        String idProva,
        String nome,
        LocalDate data,
        Float peso,
        String disciplina,
        String turma,
        AtividadeStatus status,
        Float nota
) {}