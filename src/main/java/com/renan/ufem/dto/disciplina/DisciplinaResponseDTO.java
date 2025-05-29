package com.renan.ufem.dto.disciplina;

import com.renan.ufem.domain.Disciplina;
import com.renan.ufem.enums.SituacaoType;

public record DisciplinaResponseDTO(
        String idDisciplina,
        String nome,
        String ementa,
        Integer cargaHoraria,
        String idSecretaria,
        SituacaoType situacao
) {
    public DisciplinaResponseDTO(Disciplina d) {
        this(d.getIdDisciplina(), d.getNome(), d.getEmenta(), d.getCargaHoraria(), d.getIdSecretaria(), d.getSituacao());
    }
}