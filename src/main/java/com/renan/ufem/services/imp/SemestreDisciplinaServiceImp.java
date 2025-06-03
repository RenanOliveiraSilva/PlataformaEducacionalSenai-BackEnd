package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.enums.DiaSemana;
import com.renan.ufem.enums.StatusSemestre;
import com.renan.ufem.repositories.DisciplinaRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.repositories.SemestreDisciplinaRepository;
import com.renan.ufem.repositories.SemestreRepository;
import com.renan.ufem.services.SemestreDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemestreDisciplinaServiceImp implements SemestreDisciplinaService {

    private final SemestreDisciplinaRepository repository;
    private final SemestreRepository semestreRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public SemestreDisciplina salvar(String idSemestre, String idDisciplina, String idProfessor, DiaSemana diaSemana) {

        Semestre semestre = semestreRepository.findById(idSemestre)
                .orElseThrow(() -> new RuntimeException("Semestre não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        SemestreDisciplinaId compositeId = new SemestreDisciplinaId(idSemestre, idDisciplina, idProfessor);

        SemestreDisciplina sd = new SemestreDisciplina();
        sd.setId(compositeId);
        sd.setSemestre(semestre);
        sd.setDisciplina(disciplina);
        sd.setProfessor(professor);
        sd.setDiaSemana(diaSemana);
        sd.setStatus(StatusSemestre.ANDAMENTO);

        return repository.save(sd);
    }
}
