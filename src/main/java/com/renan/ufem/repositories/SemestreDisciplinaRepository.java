package com.renan.ufem.repositories;

import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.domain.SemestreDisciplinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreDisciplinaRepository extends JpaRepository<SemestreDisciplina, SemestreDisciplinaId> {}
