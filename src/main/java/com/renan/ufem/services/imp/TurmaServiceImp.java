package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.repositories.TurmaRepository;
import com.renan.ufem.services.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaServiceImp implements TurmaService {

    private final TurmaRepository repository;

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
        return repository.save(turma);
    }
}
