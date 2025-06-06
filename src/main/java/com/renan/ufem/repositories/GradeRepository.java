package com.renan.ufem.repositories;

import com.renan.ufem.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// GradeRepository.java
public interface GradeRepository extends JpaRepository<Grade, String> {
    Optional<Grade> findByTurma_IdTurma(String id_turma);
}
