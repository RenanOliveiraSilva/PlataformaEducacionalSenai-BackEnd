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

        SemestreDisciplina entity = new SemestreDisciplina(null, semestre, disciplina, dto.professor(), dto.diaSemana());
        return toDTO(repository.save(entity));
    }

    @Override
    public List<SemestreDisciplinaResponseDTO> listar() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SemestreDisciplinaResponseDTO buscarPorId(String id) {
        SemestreDisciplina sd = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro não encontrado"));
        return toDTO(sd);
    }


    private SemestreDisciplinaResponseDTO toDTO(SemestreDisciplina sd) {
        return new SemestreDisciplinaResponseDTO(
                sd.getId(),
                sd.getSemestre().getIdSemestre(),
                sd.getDisciplina().getIdDisciplina(),
                sd.getProfessor(),
                sd.getDiaSemana()
        );
    }
}