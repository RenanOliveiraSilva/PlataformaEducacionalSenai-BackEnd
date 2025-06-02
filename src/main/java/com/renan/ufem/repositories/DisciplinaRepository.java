package com.renan.ufem.repositories;

import com.renan.ufem.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {

    Optional<Disciplina> findByNomeAndIdSecretaria(String nome, String idSecretaria);
    List<Disciplina> findAllByIdSecretaria(String idSecretaria);
}
