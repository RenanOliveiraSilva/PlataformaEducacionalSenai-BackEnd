package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.repositories.TurmaRepository;
import com.renan.ufem.services.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaServiceImp implements TurmaService {

    private final TurmaRepository repository;

    @Override
    public Turma criarTurma(TurmaDTO body) {
        Turma turma = new Turma();
        turma.setNome(body.nome());
        turma.setAno(body.ano());
        turma.setId_curso(body.id_curso());
        turma.setId_secretaria(body.id_secretaria());
        return repository.save(turma);
    }
}
