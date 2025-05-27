package com.renan.ufem.services;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;

public interface TurmaService {
    Turma criarTurma(TurmaDTO body);
}
