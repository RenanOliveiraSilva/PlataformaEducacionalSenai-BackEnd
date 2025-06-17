package com.renan.ufem.dto.disciplina;

public record DisciplinaProfessorDTO(
        String idDisciplina,
        String nomeDisciplina,
        com.renan.ufem.enums.DiaSemana diaSemana,
        Integer numeroSemestre
) {}
