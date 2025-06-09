package com.renan.ufem.dto.semestreDisciplina;

import java.util.List;

import com.renan.ufem.enums.StatusSemestre;

public record SemestreAlunoDTO(
        String idSemestre,
        int numero,
        StatusSemestre status,
        List<SemestreDisciplinaDTO> semestreDisciplinas
) {}

