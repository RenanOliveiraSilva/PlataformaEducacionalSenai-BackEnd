package com.renan.ufem.repositories;

import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.domain.SemestreDisciplinaId;
import com.renan.ufem.domain.Turma;
import com.renan.ufem.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SemestreDisciplinaRepository extends JpaRepository<SemestreDisciplina, SemestreDisciplinaId> {
    @Query("SELECT sd FROM SemestreDisciplina sd WHERE sd.professor.id = :idProfessor AND sd.diaSemana = :diaSemana AND sd.status = 'ANDAMENTO'")
    Optional<SemestreDisciplina> existsByProfessorAndDiaSemanaAndStatusAndamento(
            @Param("idProfessor") String idProfessor,
            @Param("diaSemana") DiaSemana diaSemana
    );

    @Query("""
      SELECT sd
        FROM SemestreDisciplina sd
       WHERE sd.id.idSemestre   = :idSemestre
         AND sd.id.idDisciplina = :idDisciplina
         AND sd.id.idProfessor  = :idProfessor
    """)
    Optional<SemestreDisciplina> findByChaves(
            @Param("idSemestre")   String idSemestre,
            @Param("idDisciplina") String idDisciplina,
            @Param("idProfessor")  String idProfessor
    );

    @Query("""
      SELECT DISTINCT sd.semestre.grade.turma
        FROM SemestreDisciplina sd
       WHERE sd.professor.idProfessor = :idProfessor
    """)
    List<Turma> findTurmasByProfessor(
            @Param("idProfessor") String idProfessor
    );

}
