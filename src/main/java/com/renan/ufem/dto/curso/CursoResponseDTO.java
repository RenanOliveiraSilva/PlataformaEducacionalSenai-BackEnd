package com.renan.ufem.dto.curso;


import com.renan.ufem.domain.Curso;
import com.renan.ufem.enums.SituacaoType;

public record CursoResponseDTO (
        String idCurso,
        String nome,
        Integer duracao,
        SituacaoType situacao,
        String id_secretaria
) {
    public CursoResponseDTO(Curso curso) {
        this(
                curso.getIdCurso(),
                curso.getNome(),
                curso.getDuracao(),
                curso.getSituacao(),
                curso.getIdSecretaria()
        );

    }
}

