package com.renan.ufem.services;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.dto.turma.TurmaResponseDTO;

import java.util.List;

public interface TurmaService {
    Turma criarTurma(String id_secretaria, String id_curso, TurmaDTO body);
    TurmaResponseDTO buscarTurma(String id_turma);
    List<TurmaResponseDTO> listarTurmasPorSecretaria(String idSecretaria);
}
