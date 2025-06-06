package com.renan.ufem.dto.grade;

import com.renan.ufem.domain.Grade;

public record GradeDTO(
        String idTurma,
        String idCurso
) {
    public GradeDTO(Grade grade) {
        this(
                grade.getTurma().getIdTurma(),
                grade.getIdCurso()
        );
    }
}
