package com.renan.ufem.services;

import com.renan.ufem.dto.atividade.AtividadeCreateDTO;
import com.renan.ufem.dto.atividade.AtividadeResponseDTO;

import java.util.List;

public interface AtividadeService {
    AtividadeResponseDTO criarAtividade(String id_disciplina, String id_turma, String id_professor, AtividadeCreateDTO atividade);
    List<AtividadeResponseDTO> buscarAtividadesPorAluno(String id_aluno);

}