package com.renan.ufem.dto.semestreDisciplina;

public record SemestreDisciplinaRequestDTO(
        String idSemestre,
        String idDisciplina,
        String professor,
        String diaSemana
) {}

