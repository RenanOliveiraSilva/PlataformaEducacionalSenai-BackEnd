package com.renan.ufem.repositories;

import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.domain.SemestreDisciplinaId;
import com.renan.ufem.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SemestreDisciplinaRepository extends JpaRepository<SemestreDisciplina, SemestreDisciplinaId> {
    @Query("SELECT sd FROM SemestreDisciplina sd WHERE sd.professor.id = :idProfessor AND sd.diaSemana = :diaSemana AND sd.status = 'ANDAMENTO'")
    Optional<SemestreDisciplina> existsByProfessorAndDiaSemanaAndStatusAndamento(
            @Param("idProfessor") String idProfessor,
            @Param("diaSemana") DiaSemana diaSemana
    );

}
