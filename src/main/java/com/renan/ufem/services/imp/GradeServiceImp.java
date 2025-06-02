package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.dto.grade.GradeDTO;
import com.renan.ufem.repositories.GradeRepository;
import com.renan.ufem.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImp implements GradeService {
    private final GradeRepository repository;

    @Override
    public Grade criarGrade(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setIdTurma(dto.idTurma());
        grade.setIdCurso(dto.idCurso());
        return repository.save(grade);
    }

    @Override
    public List<Grade> listarGrades() {
        return repository.findAll();
    }
}

