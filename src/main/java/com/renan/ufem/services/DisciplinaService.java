package com.renan.ufem.services;

import com.renan.ufem.domain.Disciplina;
import com.renan.ufem.dto.disciplina.DisciplinaDTO;
import com.renan.ufem.dto.disciplina.DisciplinaResponseDTO;

import java.util.List;

public interface DisciplinaService {
    DisciplinaResponseDTO criar(String idSecretaria, DisciplinaDTO body);
    DisciplinaResponseDTO editar(String idDisciplina, DisciplinaDTO body);
    DisciplinaResponseDTO buscarPorId(String idDisciplina);
    List<DisciplinaResponseDTO> buscarPorSecretaria(String idSecretaria);
    DisciplinaResponseDTO alterarSituacao(String idDisciplina);
}