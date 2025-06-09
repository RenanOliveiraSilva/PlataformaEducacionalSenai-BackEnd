package com.renan.ufem.services;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.dto.aluno.AlunoUpdateDTO;
import com.renan.ufem.dto.curso.CursoDTO;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreAlunoDTO;

import java.util.List;

public interface AlunoService {
    Aluno loginAluno(AlunoLoginRequestDTO body);
    Aluno criarAluno(AlunoDTO body, String id_turma);
    Aluno editarAluno(AlunoUpdateDTO body, String id_aluno);
    AlunoDTO alterarSituacaoAluno(String id_aluno);
    AlunoDTO buscarAluno(String id_aluno);
    CursoDTO buscarAlunoCurso(String id_aluno);
    List<SemestreAlunoDTO>  buscarPeriodoAluno(String id_aluno);

}
