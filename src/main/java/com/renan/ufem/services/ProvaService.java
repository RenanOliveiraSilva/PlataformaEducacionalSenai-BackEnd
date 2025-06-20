package com.renan.ufem.services;

import com.renan.ufem.dto.prova.ProvaCreateDTO;
import com.renan.ufem.dto.prova.ProvaResponseDTO;

import java.util.List;

public interface ProvaService {
    ProvaResponseDTO criarProva(String idTurma, String idDisciplina, ProvaCreateDTO dto);
    List<ProvaResponseDTO> buscarProvasPorAluno(String idAluno);
    void concluirProva(String idAluno, String idProva);
    void avaliarProva(String idAluno, String idProva, Float nota);
}
