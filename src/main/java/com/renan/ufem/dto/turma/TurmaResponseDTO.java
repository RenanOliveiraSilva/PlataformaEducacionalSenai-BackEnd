package com.renan.ufem.dto.turma;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.aluno.AlunoResponseDTO;
import java.util.List;

public record TurmaResponseDTO(
        String idTurma,
        String nome,
        String ano,
        String idCurso,
        String idSecretaria,
        List<AlunoResponseDTO> alunos
) {
    public TurmaResponseDTO(Turma turma) {
        this(
            turma.getIdTurma(),
            turma.getNome(),
            turma.getAno(),
            turma.getIdCurso(),
            turma.getIdSecretaria(),
            turma.getAlunos().stream().map(AlunoResponseDTO::new).toList()

        );
    }
}
