package com.renan.ufem.dto.curso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.enums.TurnoType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CursoEditarDTO (
        String nome,
        Integer duracao,
        TurnoType turno,
        SituacaoType situacao,
        String id_secretaria
) { }
