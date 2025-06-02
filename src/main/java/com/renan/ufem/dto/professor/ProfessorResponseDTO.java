package com.renan.ufem.dto.professor;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.enums.SituacaoType;

public record ProfessorResponseDTO(
        String idProfessor,
        String nome,
        String email,
        SituacaoType situacao
) {
    public ProfessorResponseDTO(Professor professor) {
        this(professor.getIdProfessor(), professor.getNome(), professor.getEmail(), professor.getSituacao());
    }
}