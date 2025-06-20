package com.renan.ufem.repositories;

import com.renan.ufem.domain.Prova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvaRepository extends JpaRepository<Prova, String> {
    List<Prova> findByTurma_IdTurma(String idTurma);
}