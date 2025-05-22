package com.renan.ufem.services;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.professor.ProfessorRegisterRequestDTO;

public interface ProfessorService {
    Professor criarProfessor(ProfessorRegisterRequestDTO professor, String idSecretaria);
}
