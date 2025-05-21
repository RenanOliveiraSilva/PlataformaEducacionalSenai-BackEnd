package com.renan.ufem.dto.curso;

import com.renan.ufem.enums.TurnoType;

public record CursoDTO (
        String nome,
        int duracao,
        String id_secretaria,
        TurnoType turno
) { }
