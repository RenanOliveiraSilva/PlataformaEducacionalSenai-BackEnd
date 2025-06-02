package com.renan.ufem.dto.turma;

import com.renan.ufem.enums.TurnoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TurmaDTO(
        @NotBlank(message = "Nome da turma é obrigatório")
        String nome,
        @NotBlank(message = "Ano da turma é obrigatório")
        @Pattern(regexp = "\\d{4}", message = "Ano deve conter 4 dígitos")
        String ano,
        TurnoType turno
) {}
