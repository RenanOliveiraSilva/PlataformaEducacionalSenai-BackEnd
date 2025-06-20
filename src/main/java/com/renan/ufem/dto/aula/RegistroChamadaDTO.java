package com.renan.ufem.dto.aula;

import java.time.LocalDate;
import java.util.List;

public record RegistroChamadaDTO(
        String conteudo,
        LocalDate dataAula,
        String idTurma,
        String idDisciplina,
        String idProfessor,
        List<String> alunosPresentes
) {}

