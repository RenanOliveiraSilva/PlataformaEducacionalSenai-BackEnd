package com.renan.ufem.dto.semestre;

import com.renan.ufem.enums.StatusSemestre;

public record SemestreDTO(Integer numero, String idGrade, StatusSemestre status) {}
