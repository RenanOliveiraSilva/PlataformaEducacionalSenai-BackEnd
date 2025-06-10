package com.renan.ufem.dto.atividade;

// Mini-DTO com as principais informações da turma
public record TurmaInfo(
        String nomeTurma,
        String ano,
        String turno,
        String situacao
) {}
