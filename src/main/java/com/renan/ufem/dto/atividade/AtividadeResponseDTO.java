package com.renan.ufem.dto.atividade;

import com.renan.ufem.enums.AtividadeStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtividadeResponseDTO(
        String idAtividade,
        String nome,
        String descricao,
        LocalDate dataEntrega,
        Float peso,
        LocalDateTime dataCadastro,
        DisciplinaInfo disciplina,
        TurmaInfo turma,
        ProfessorInfo professor,
        AtividadeStatus statusAtividade
) {}
