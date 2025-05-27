package com.renan.ufem.services;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;

public interface AlunoService {
    Aluno loginAluno(AlunoLoginRequestDTO body);
    Aluno criarAluno(AlunoDTO body, String id_turma);
}
