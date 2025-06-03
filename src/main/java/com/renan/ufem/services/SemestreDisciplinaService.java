package com.renan.ufem.services;
import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.enums.DiaSemana;

public interface SemestreDisciplinaService {
    SemestreDisciplina salvar(String idSemestre, String idDisciplina, String idProfessor, DiaSemana diaSemana);

}