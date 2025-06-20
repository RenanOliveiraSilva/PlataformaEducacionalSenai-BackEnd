package com.renan.ufem.repositories;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.domain.NotaProva;
import com.renan.ufem.domain.Prova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaProvaRepository extends JpaRepository<NotaProva, String> {
    List<NotaProva> findByAluno_IdAluno(String idAluno);
    Optional<NotaProva> findByAluno_IdAlunoAndProva_IdProva(String idAluno, String idProva);
    boolean existsByAlunoAndProva(Aluno aluno, Prova prova);
}