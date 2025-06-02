package com.renan.ufem.repositories;

import com.renan.ufem.domain.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre, String> {}