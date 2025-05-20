package com.renan.ufem.repositories;

import com.renan.ufem.domain.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository  extends JpaRepository<Professor, String> {
    Optional<Professor> findByCPF(String cpf);
}
