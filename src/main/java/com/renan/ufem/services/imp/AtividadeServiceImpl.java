package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Atividade;
import com.renan.ufem.domain.Disciplina;
import com.renan.ufem.domain.Professor;
import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.atividade.*;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.AtividadeRepository;
import com.renan.ufem.repositories.DisciplinaRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.repositories.TurmaRepository;
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

    @Override
    public AtividadeResponseDTO criarAtividade(String id_disciplina, String id_turma, String id_professor, AtividadeCreateDTO dto) {
        // 1) Carrega entidades relacionadas ou lança 404
        Disciplina disciplina = disciplinaRepository.findById(id_disciplina)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        Turma turma = turmaRepository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        Professor professor = professorRepository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

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

        return new AtividadeResponseDTO(
                salva.getNome(),
                salva.getDescricao(),
                salva.getDataEntrega(),
                salva.getPeso(),
                salva.getDataCadastro(),
                // mini-DTOs de relacionamentos
                new DisciplinaInfo(salva.getDisciplina().getNome()),
                new TurmaInfo(
                        salva.getTurma().getNome(),
                        salva.getTurma().getAno(),
                        salva.getTurma().getTurno().name(),
                        salva.getTurma().getSituacao().name()
                ),
                new ProfessorInfo(salva.getProfessor().getNome())
        );
    }

}
