package com.renan.ufem.repositories;

import com.renan.ufem.domain.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SemestreRepository extends JpaRepository<Semestre, String> {
    Optional<Semestre> findByNumeroAndGradeIdGrade(Integer numero, String idGrade);
    Optional<Semestre> findById(String id_semestre);
    List<Semestre> findAllByGrade_IdGrade(String idGrade);

    @Query("""
       SELECT s 
       FROM Semestre s 
       JOIN FETCH s.semestreDisciplinas sd 
       JOIN FETCH sd.disciplina d 
       JOIN FETCH sd.professor p
       WHERE s.grade.idGrade = :idGrade
    """)
    List<Semestre> findAllByGradeComDisciplinasEProfessores(@Param("idGrade") String idGrade);
}