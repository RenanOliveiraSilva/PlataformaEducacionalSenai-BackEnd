package com.renan.ufem.services;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.professor.ProfessorDTO;
import com.renan.ufem.dto.professor.ProfessorEditarDTO;
import com.renan.ufem.dto.professor.ProfessorLoginRequestDTO;

public interface ProfessorService {
    Professor criarProfessor(ProfessorDTO professor, String idSecretaria);
    Professor loginProfessor(ProfessorLoginRequestDTO login);
    ProfessorDTO buscarProfessor(String id_professor);
    ProfessorDTO alterarSituacaoProfessor(String id_professor);
    Professor editarProfessor(String id_professor, ProfessorEditarDTO body);
}
