package com.renan.ufem.repositories;

import com.renan.ufem.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository  extends JpaRepository<Professor, String> {
    Optional<Professor> findByCPF(String cpf);
    Optional<Professor> findById(String id);
    Optional<Professor> findByEmail(String email);
    List<Professor> findByIdSecretaria(String idSecretaria);
}
