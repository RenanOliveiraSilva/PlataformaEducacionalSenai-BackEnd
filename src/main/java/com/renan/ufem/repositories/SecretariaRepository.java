package com.renan.ufem.repositories;

import com.renan.ufem.domain.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SecretariaRepository extends JpaRepository<Secretaria, String> {

    Optional<Secretaria> findByEmail(String email);
    Optional<Secretaria> findById(String id);
}
