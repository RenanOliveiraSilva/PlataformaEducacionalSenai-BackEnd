package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.dto.aula.RegistroChamadaDTO;
import com.renan.ufem.enums.StatusFrequencia;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.ChamadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadaServiceImp implements ChamadaService {

    private final AulaRepository aulaRepository;
    private final FrequenciaRepository frequenciaRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public void registrarChamada(RegistroChamadaDTO dto) {
        Turma turma = turmaRepository.findById(dto.idTurma())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.idDisciplina())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
        Professor professor = professorRepository.findById(dto.idProfessor())
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

        Aula aula = new Aula();
        aula.setConteudo(dto.conteudo());
        aula.setDataAula(dto.dataAula());
        aula.setTurma(turma);
        aula.setDisciplina(disciplina);
        aula.setProfessor(professor);

        Aula aulaSalva = aulaRepository.save(aula);

        List<Aluno> alunos = alunoRepository.findByTurma_IdTurma(dto.idTurma());

        for (Aluno aluno : alunos) {
            Frequencia freq = new Frequencia();
            freq.setAluno(aluno);
            freq.setAula(aulaSalva);
            freq.setData(dto.dataAula());
            freq.setStatus(dto.alunosPresentes().contains(aluno.getIdAluno()) ? StatusFrequencia.PRESENTE : StatusFrequencia.AUSENTE);
            frequenciaRepository.save(freq);
        }
    }
}

