package com.renan.ufem.repositories;

import com.renan.ufem.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, String> {
    Optional<Curso> findByNome(String nome);
    Optional<Curso> findById(String id_curso);
    List<Curso> findAllByIdSecretaria(String idSecretaria);
}
