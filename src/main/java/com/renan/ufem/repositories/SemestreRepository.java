package com.renan.ufem.repositories;

import com.renan.ufem.domain.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SemestreRepository extends JpaRepository<Semestre, String> {
    Optional<Semestre> findByNumeroAndGradeIdGrade(Integer numero, String idGrade);
    Optional<Semestre> findById(String id_semestre);
    List<Semestre> findAllByGrade_IdGrade(String idGrade);
}