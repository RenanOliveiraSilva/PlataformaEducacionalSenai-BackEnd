package com.renan.ufem.services;
import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaRequestDTO;
import com.renan.ufem.dto.turma.TurmaResponseDTO;

import java.util.List;

public interface SemestreDisciplinaService {
    SemestreDisciplina salvar(String idSemestre, String idDisciplina, String idProfessor, SemestreDisciplinaRequestDTO body);
    //SemestreDisciplina concluir(String idSemestre, String idDisciplina, String idProfessor);
    List<TurmaResponseDTO> getTurmasPorProfessor(String idProfessor);
}