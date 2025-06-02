package com.renan.ufem.dto.aluno;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.enums.SituacaoType;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String idAluno,
        String nome,
        String email,
        String matricula,
        String telefone,
        SituacaoType situacao,
        LocalDate data_nasc
) {
    public AlunoResponseDTO(Aluno aluno) {
        this(
                aluno.getIdAluno(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getTelefone(),
                aluno.getSituacao(),
                aluno.getData_nasc()
        );
    }
}
