package com.renan.ufem.services;

import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaRequestDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaResponseDTO;

import java.util.List;

public interface SemestreDisciplinaService {
    SemestreDisciplinaResponseDTO criar(SemestreDisciplinaRequestDTO dto);
    List<SemestreDisciplinaResponseDTO> listar();
    SemestreDisciplinaResponseDTO buscarPorId(String id);

}
