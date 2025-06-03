package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.dto.turma.TurmaResponseDTO;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.GradeRepository;
import com.renan.ufem.repositories.TurmaRepository;
import com.renan.ufem.services.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaServiceImp implements TurmaService {

    private final TurmaRepository repository;
    private final GradeRepository gradeRepository;

    @Override
    public Turma criarTurma(String id_secretaria, String id_curso, TurmaDTO body) {
        boolean turmaExistente = repository.existsByNomeAndAnoAndIdSecretaria(
                body.nome(), body.ano(), id_secretaria
        );

        if (turmaExistente) {
            throw new ConflictException("Já existe uma turma com este nome e ano para esta secretaria.");
        }

        Turma turma = new Turma();
        turma.setNome(body.nome());
        turma.setAno(body.ano());
        turma.setTurno(body.turno());
        turma.setIdCurso(id_curso);
        turma.setIdSecretaria(id_secretaria);
        turma.setSituacao(SituacaoType.ATIVO);

        Turma newTurma = repository.save(turma);

        if(newTurma == null) { new ConflictException("Turma não encontrada"); }

        Grade grade = new Grade();
        grade.setTurma(newTurma);
        grade.setIdCurso(id_curso);
        gradeRepository.save(grade);

        return turma;
    }

    @Override
    public TurmaResponseDTO buscarTurma(String id_turma) {
        Turma turma = this.repository.findById(id_turma)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        return new TurmaResponseDTO(turma);
    }
}
