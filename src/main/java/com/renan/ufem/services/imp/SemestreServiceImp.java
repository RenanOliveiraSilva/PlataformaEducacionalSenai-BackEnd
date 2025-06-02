package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.GradeRepository;
import com.renan.ufem.repositories.SemestreRepository;
import com.renan.ufem.services.SemestreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemestreServiceImp implements SemestreService {
    private final SemestreRepository repository;
    private final GradeRepository gradeRepository;

    @Override
    public Semestre criarSemestre(SemestreDTO dto) {
        Grade grade = gradeRepository.findById(dto.idGrade())
                .orElseThrow(() -> new NotFoundException("Grade não encontrada"));

        Semestre semestre = new Semestre();
        semestre.setNumero(dto.numero());
        semestre.setGrade(grade);
        return repository.save(semestre);
    }

    @Override
    public List<Semestre> listarPorGrade(String idGrade) {
        return repository.findAll().stream()
                .filter(s -> s.getGrade().getIdGrade().equals(idGrade))
                .toList();
    }
}
