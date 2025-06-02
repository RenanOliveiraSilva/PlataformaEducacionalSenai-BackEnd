package com.renan.ufem.services;

import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.semestre.SemestreDTO;

import java.util.List;

public interface SemestreService {
    Semestre criarSemestre(SemestreDTO dto);
    List<Semestre> listarPorGrade(String idGrade);
}
