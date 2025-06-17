package com.renan.ufem.repositories;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.domain.Atividade;
import com.renan.ufem.domain.AtividadeAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtividadeAlunoRepository extends JpaRepository<AtividadeAluno, String> {
    List<AtividadeAluno> findByAluno_IdAluno(String idAluno);
    boolean existsByAlunoAndAtividade(Aluno aluno, Atividade atividade);
    Optional<AtividadeAluno> findByAluno_IdAlunoAndAtividade_IdAtividade(String idAluno, String idAtividade);


}