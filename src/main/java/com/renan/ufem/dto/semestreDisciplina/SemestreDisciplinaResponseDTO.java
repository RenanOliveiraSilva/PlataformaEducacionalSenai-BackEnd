package com.renan.ufem.dto.semestreDisciplina;

public record SemestreDisciplinaResponseDTO(
        String id,
        String idSemestre,
        String idDisciplina,
        String professor,
        String diaSemana
) {}