package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.dto.atividade.*;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeServiceImpl implements AtividadeService {
    private final AtividadeRepository atividadeRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final SemestreDisciplinaRepository sdRepository;
    private final AlunoRepository alunoRespository;

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

        // 3) Persiste
        Atividade salva = atividadeRepository.save(atividade);

        return toDTO(salva);
    }

    @Override
    public List<AtividadeResponseDTO> buscarAtividadesPorAluno(String idAluno) {
        // 1) Verifica se o aluno existe
        Aluno aluno = this.alunoRespository.findById(idAluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        // 2) Pega o id da turma do aluno
        String idTurma = aluno.getTurma().getIdTurma();

        // 3) Busca as atividades dessa turma
        List<Atividade> atividades = atividadeRepository.buscarAtividadesPorAluno(idTurma);
        if (atividades.isEmpty()) {
            throw new NotFoundException("Nenhuma atividade encontrada para este aluno");
        }

        // 4) Converte para DTO e retorna
        return atividades.stream()
                .map(this::toDTO)
                .toList();
    }

    private AtividadeResponseDTO toDTO(Atividade a) {
        return new AtividadeResponseDTO(
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
                new ProfessorInfo(a.getProfessor().getNome())
        );
    }

}
