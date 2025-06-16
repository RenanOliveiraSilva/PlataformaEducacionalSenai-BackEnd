package com.renan.ufem.repositories;

import com.renan.ufem.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, String> {
    @Query("""
      SELECT a
        FROM Atividade a
       WHERE a.turma.idTurma = :idTurma
    """)
    List<Atividade> buscarAtividadesPorAluno(@Param("idTurma") String idTurma);
}
