package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.grade.GradeDTO;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.repositories.GradeRepository;
import com.renan.ufem.repositories.TurmaRepository;
import com.renan.ufem.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImp implements GradeService {
    private final GradeRepository repository;
    private final TurmaRepository turmaRepository;

    @Override
    public Grade criarGrade(GradeDTO dto) {
        Turma turma = this.turmaRepository.findById(dto.idTurma())
                .orElseThrow(() -> new ConflictException("Turma não encontrada"));

        Grade grade = new Grade();
        grade.setTurma(turma);
        grade.setIdCurso(dto.idCurso());
        return repository.save(grade);
    }

    @Override
    public List<Grade> listarGrades() {
        return repository.findAll();
    }
}

