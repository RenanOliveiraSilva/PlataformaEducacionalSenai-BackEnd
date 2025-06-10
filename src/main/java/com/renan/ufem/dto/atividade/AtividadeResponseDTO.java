package com.renan.ufem.dto.atividade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

// DTO principal de Atividade (para resposta)
public record AtividadeResponseDTO(
        String nome,
        String descricao,
        LocalDate dataEntrega,
        Float peso,
        LocalDateTime dataCadastro,
        DisciplinaInfo disciplina,
        TurmaInfo turma,
        ProfessorInfo professor
) {}

