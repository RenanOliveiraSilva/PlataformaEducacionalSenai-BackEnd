package com.renan.ufem.dto.semestreDisciplina;

import com.renan.ufem.enums.DiaSemana;
import com.renan.ufem.enums.StatusSemestre;

    public record SemestreDisciplinaDTO(IdSemestreDisciplinaDTO id, DiaSemana diaSemana, StatusSemestre status) {}
