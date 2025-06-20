package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.dto.prova.ProvaCreateDTO;
import com.renan.ufem.dto.prova.ProvaResponseDTO;
import com.renan.ufem.enums.AtividadeStatus;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvaServiceImp implements ProvaService {
    private final ProvaRepository provaRepository;
    private final NotaProvaRepository notaProvaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public ProvaResponseDTO criarProva(String idTurma, String idDisciplina, ProvaCreateDTO dto) {
        Turma turma = turmaRepository.findById(idTurma).orElseThrow(() -> new NotFoundException("Turma não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        Prova prova = new Prova();
        prova.setNome(dto.nome());
        prova.setData(dto.data());
        prova.setPeso(dto.peso());
        prova.setTurma(turma);
        prova.setDisciplina(disciplina);

        return toDTO(provaRepository.save(prova), null, null);
    }

    @Override
    public List<ProvaResponseDTO> buscarProvasPorAluno(String idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        String idTurma = aluno.getTurma().getIdTurma();

        List<Prova> provas = provaRepository.findByTurma_IdTurma(idTurma);
        List<NotaProva> notas = notaProvaRepository.findByAluno_IdAluno(idAluno);

        Map<String, AtividadeStatus> statusPorProva = notas.stream().collect(Collectors.toMap(
                n -> n.getProva().getIdProva(),
                NotaProva::getStatusProva
        ));

        Map<String, Float> notaPorProva = notas.stream().collect(Collectors.toMap(
                n -> n.getProva().getIdProva(),
                NotaProva::getNota
        ));

        return provas.stream()
                .map(p -> toDTO(
                        p,
                        statusPorProva.getOrDefault(p.getIdProva(), null),
                        notaPorProva.getOrDefault(p.getIdProva(), null)))
                .toList();
    }

    @Override
    public void concluirProva(String idAluno, String idProva) {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        Prova prova = provaRepository.findById(idProva).orElseThrow(() -> new NotFoundException("Prova não encontrada"));

        if (notaProvaRepository.existsByAlunoAndProva(aluno, prova)) {
            throw new ConflictException("Esta prova já foi concluída por este aluno.");
        }

        NotaProva nota = new NotaProva();
        nota.setAluno(aluno);
        nota.setProva(prova);
        nota.setStatusProva(AtividadeStatus.AGUARDANDO);
        notaProvaRepository.save(nota);
    }

    @Override
    public void avaliarProva(String idAluno, String idProva, Float nota) {
        NotaProva np = notaProvaRepository.findByAluno_IdAlunoAndProva_IdProva(idAluno, idProva)
                .orElseThrow(() -> new NotFoundException("Entrega de prova não encontrada"));

        np.setNota(nota);
        np.setStatusProva(AtividadeStatus.CONCLUIDO);
        notaProvaRepository.save(np);
    }

    private ProvaResponseDTO toDTO(Prova p, AtividadeStatus status, Float nota) {
        return new ProvaResponseDTO(
                p.getIdProva(),
                p.getNome(),
                p.getData(),
                p.getPeso(),
                p.getDisciplina().getNome(),
                p.getTurma().getNome(),
                status,
                nota
        );
    }
}

