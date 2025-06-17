package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.dto.atividade.*;
import com.renan.ufem.enums.AtividadeStatus;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtividadeServiceImpl implements AtividadeService {
    private final AtividadeRepository atividadeRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final SemestreDisciplinaRepository sdRepository;
    private final AlunoRepository alunoRespository;
    private final AtividadeAlunoRepository alunoAtividadeRepository;

    @Override
    public AtividadeResponseDTO criarAtividade(String id_disciplina, String id_turma, String id_professor, AtividadeCreateDTO dto) {
        // 1) Carrega entidades relacionadas ou lança 404


        Disciplina disciplina = disciplinaRepository.findById(id_disciplina)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        Turma turma = turmaRepository.findById(id_turma)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        Professor professor = professorRepository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

        Semestre semestre = turma.getGrade().getSemestres().getLast();

        SemestreDisciplina exist = sdRepository.findByChaves(semestre.getIdSemestre(), id_disciplina, id_professor)
                                               .orElseThrow(() -> new NotFoundException("Disciplina nesse semestre com esse professor não encontrado"));

        // 2) Monta a entidade Atividade
        Atividade atividade = new Atividade();
        atividade.setNome(dto.nome());
        atividade.setDescricao(dto.descricao());
        atividade.setDataEntrega(dto.dataEntrega());
        atividade.setPeso(dto.peso());
        atividade.setDisciplina(disciplina);
        atividade.setTurma(turma);
        atividade.setProfessor(professor);
        atividade.setAtividadeStatus(AtividadeStatus.PENDENTE);

        // 3) Persiste
        Atividade salva = atividadeRepository.save(atividade);

        return toDTO(salva);
    }

    @Override
    public List<AtividadeResponseDTO> buscarAtividadesPorAluno(String idAluno) {
        Aluno aluno = alunoRespository.findById(idAluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        String idTurma = aluno.getTurma().getIdTurma();

        List<Atividade> atividades = atividadeRepository.buscarAtividadesPorAluno(idTurma);
        if (atividades.isEmpty()) {
            throw new NotFoundException("Nenhuma atividade encontrada para este aluno");
        }

        // Busca os status da tabela atividadeAluno
        List<AtividadeAluno> atividadesAluno = alunoAtividadeRepository.findByAluno_IdAluno(idAluno);

        // Map de status
        Map<String, String> statusPorAtividade = atividadesAluno.stream()
                .collect(Collectors.toMap(
                        aa -> aa.getAtividade().getIdAtividade(),
                        aa -> aa.getStatus().name()
                ));

        // Map de notas
        Map<String, Float> notaPorAtividade = atividadesAluno.stream()
                .collect(Collectors.toMap(
                        aa -> aa.getAtividade().getIdAtividade(),
                        AtividadeAluno::getNota,
                        (n1, n2) -> n1 // resolve duplicata mantendo a primeira
                ));

        return atividades.stream()
                .map(atividade -> toDTOComStatusAluno(atividade, statusPorAtividade, notaPorAtividade))
                .toList();
    }


    @Override
    public void concluirAtividade(String idAluno, String idAtividade) {
        Aluno aluno = alunoRespository.findById(idAluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        Atividade atividade = atividadeRepository.findById(idAtividade)
                .orElseThrow(() -> new NotFoundException("Atividade não encontrada"));

        boolean jaConcluiu = alunoAtividadeRepository.existsByAlunoAndAtividade(aluno, atividade);

        if (jaConcluiu) {
            throw new ConflictException("Esta atividade já foi concluída por este aluno.");
        }

        AtividadeAluno aa = new AtividadeAluno();
        aa.setAluno(aluno);
        aa.setAtividade(atividade);
        aa.setStatus(AtividadeStatus.AGUARDANDO);
        aa.setDataEntrega(LocalDate.now());

        alunoAtividadeRepository.save(aa);
    }

    public void avaliarAtividade(String idAtividade, String idAluno, Float nota) {
        AtividadeAluno aa = alunoAtividadeRepository.findByAluno_IdAlunoAndAtividade_IdAtividade(idAluno, idAtividade)
                .orElseThrow(() -> new NotFoundException("Entrega de atividade não encontrada"));

        aa.setNota(nota);
        aa.setStatus(AtividadeStatus.CONCLUIDO); // ou outro status, se tiver

        alunoAtividadeRepository.save(aa);
    }


    private AtividadeResponseDTO toDTO(Atividade a) {
        return new AtividadeResponseDTO(
                        a.getIdAtividade(),
                        a.getNome(),
                        a.getDescricao(),
                        a.getDataEntrega(),
                        a.getPeso(),
                a.getDataCadastro(),
                new DisciplinaInfo(a.getDisciplina().getNome()),
                new TurmaInfo(
                                a.getTurma().getNome(),
                                a.getTurma().getAno(),
                                a.getTurma().getTurno().name(),
                                a.getTurma().getSituacao().name()
                        ),
                new ProfessorInfo(a.getProfessor().getNome()),
                a.getAtividadeStatus()

                );
    }

    private AtividadeResponseDTO toDTOComStatusAluno(
            Atividade a,
            Map<String, String> statusPorAtividade,
            Map<String, Float> notaPorAtividade
    ) {
        AtividadeStatus statusFinal = AtividadeStatus.valueOf(
                statusPorAtividade.getOrDefault(a.getIdAtividade(), a.getAtividadeStatus().name())
        );

        Float notaFinal = notaPorAtividade.get(a.getIdAtividade());

        return new AtividadeResponseDTO(
                a.getIdAtividade(),
                a.getNome(),
                a.getDescricao(),
                a.getDataEntrega(),
                a.getPeso(),
                a.getDataCadastro(),
                new DisciplinaInfo(a.getDisciplina().getNome()),
                new TurmaInfo(
                        a.getTurma().getNome(),
                        a.getTurma().getAno(),
                        a.getTurma().getTurno().name(),
                        a.getTurma().getSituacao().name()
                ),
                new ProfessorInfo(a.getProfessor().getNome()),
                statusFinal
                // 👈 incluído aqui
        );
    }




}
