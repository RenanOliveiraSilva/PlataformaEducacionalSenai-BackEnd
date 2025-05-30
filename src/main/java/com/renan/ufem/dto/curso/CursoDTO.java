package com.renan.ufem.dto.curso;

import com.renan.ufem.domain.Curso;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.enums.TurnoType;

public record CursoDTO (
        String nome,
        Integer duracao,
        TurnoType turno,
        SituacaoType situacao,
        String id_secretaria
) {
    public CursoDTO(Curso curso) {
        this(
                curso.getNome(),
                curso.getDuracao(),
                curso.getTurno(),
                curso.getSituacao(),
                curso.getIdSecretaria()
        );

    }
}
