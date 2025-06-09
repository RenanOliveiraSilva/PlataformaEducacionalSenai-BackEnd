package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.enums.StatusSemestre;
import com.renan.ufem.exceptions.ConflictException;
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

        // Verifica se já existe um semestre com esse número na mesma grade
        boolean existe = repository.findByNumeroAndGradeIdGrade(dto.numero(), dto.idGrade()).isPresent();
        if (existe) {
            throw new ConflictException("Já existe um semestre com esse número para essa grade.");
        }


        Semestre semestre = new Semestre();
        semestre.setNumero(dto.numero());
        semestre.setGrade(grade);
        semestre.setStatus(StatusSemestre.ANDAMENTO);
        return repository.save(semestre);
    }

    @Override
    public Semestre alterarSituacaoSemestre(String id_semestre){
        Semestre semestre = repository.findById(id_semestre)
                .orElseThrow(() -> new NotFoundException("Semestre não encontrado"));

        if(semestre.getStatus() == StatusSemestre.ANDAMENTO) {
            semestre.setStatus(StatusSemestre.CONCLUIDO);
        } else {
            semestre.setStatus(StatusSemestre.ANDAMENTO);
        }

        return repository.save(semestre);
    }


    @Override
    public List<Semestre> listarPorGrade(String idGrade) {
        return repository.findAll().stream()
                .filter(s -> s.getGrade().getIdGrade().equals(idGrade))
                .toList();
    }
}
