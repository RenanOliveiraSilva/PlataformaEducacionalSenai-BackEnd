package com.renan.ufem.repositories;

import com.renan.ufem.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, String> {
    Optional<Turma> findById(String id_turma);
    boolean existsByNomeAndAnoAndIdSecretaria(String nome, String ano, String idSecretaria);

}
