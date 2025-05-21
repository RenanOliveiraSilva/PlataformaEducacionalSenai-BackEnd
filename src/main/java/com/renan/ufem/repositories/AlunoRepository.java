package com.renan.ufem.repositories;

import com.renan.ufem.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Optional<Aluno> findByCPF(String cpf);
}
