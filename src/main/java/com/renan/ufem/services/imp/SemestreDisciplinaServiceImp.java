package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Disciplina;
import com.renan.ufem.domain.Semestre;
import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaRequestDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaResponseDTO;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.SemestreDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemestreDisciplinaServiceImp implements SemestreDisciplinaService {

    private final SemestreDisciplinaRepository repository;
    private final SemestreRepository semestreRepository;
    private final DisciplinaRepository disciplinaRepository;

    @Override
    public SemestreDisciplinaResponseDTO criar(SemestreDisciplinaRequestDTO dto) {
        Semestre semestre = semestreRepository.findById(dto.idSemestre())
                .orElseThrow(() -> new NotFoundException("Semestre não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.idDisciplina())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));



    }




}