package com.renan.ufem.services.imp;

import com.renan.ufem.domain.*;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.dto.aluno.AlunoUpdateDTO;
import com.renan.ufem.dto.curso.CursoDTO;
import com.renan.ufem.dto.professor.ProfessorDTO;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.dto.semestreDisciplina.IdSemestreDisciplinaDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreAlunoDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaDTO;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.*;
import com.renan.ufem.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoServiceImp implements AlunoService {
    private final AlunoRepository repository;
    private final CursoRepository cursoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TurmaRepository turmaRepository;
    private final SemestreRepository semestreRepository;

    @Override
    public Aluno loginAluno(AlunoLoginRequestDTO body) {

        Aluno aluno = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Aluno not found"));

        if (passwordEncoder.matches(body.senha(), aluno.getSenha())){
            return aluno;
        };

        throw new ConflictException("Login inválido.");
    }

    @Override
    public Aluno criarAluno(AlunoDTO body, String id_turma) {

        Optional<Aluno> alunoPorCPF = this.repository.findByCPF(body.CPF());
        if (alunoPorCPF.isPresent()) {
            throw new ConflictException("Já existe um aluno com este CPF.");
        }

        Optional<Aluno> alunoPorEmail = this.repository.findByEmail(body.email());
        if (alunoPorEmail.isPresent()) {
            throw new ConflictException("Já existe um aluno com este e-mail.");
        }

        // Busca a turma pelo ID
        Turma turma = this.turmaRepository.findById(id_turma)
                .orElseThrow(() -> new ConflictException("Turma não encontrada"));

        Aluno newAluno = new Aluno();
        newAluno.setNome(body.nome());
        newAluno.setCPF(body.CPF());
        newAluno.setEmail(body.email());
        newAluno.setSenha(passwordEncoder.encode(body.senha()));
        newAluno.setLogradouro(body.logradouro());
        newAluno.setNumero(body.numero());
        newAluno.setBairro(body.bairro());
        newAluno.setCidade(body.cidade());
        newAluno.setUF(body.UF());
        newAluno.setTelefone(body.telefone());
        newAluno.setSexo(body.sexo());
        newAluno.setData_nasc(body.data_nasc());
        newAluno.setMatricula(body.matricula());
        newAluno.setSituacao(SituacaoType.ATIVO);
        newAluno.setTurma(turma);

        return this.repository.save(newAluno);
    }

    @Override
    public Aluno editarAluno(AlunoUpdateDTO body, String id_aluno) {
        Aluno aluno = this.repository.findById(id_aluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        // Verifica e atualiza o e-mail
        if (body.email() != null && !body.email().equals(aluno.getEmail())) {
            this.repository.findByEmail(body.email()).ifPresent(e -> {
                throw new ConflictException("E-mail já está em uso.");
            });
            aluno.setEmail(body.email());
        }

        // Verifica e atualiza o CPF
        if (body.CPF() != null && !body.CPF().equals(aluno.getCPF())) {
            this.repository.findByCPF(body.CPF()).ifPresent(e -> {
                throw new ConflictException("CPF já está em uso.");
            });
            aluno.setCPF(body.CPF());
        }

        // Atualiza demais campos se presentes
        if (body.nome() != null) aluno.setNome(body.nome());
        if (body.logradouro() != null) aluno.setLogradouro(body.logradouro());
        if (body.numero() != null) aluno.setNumero(body.numero());
        if (body.bairro() != null) aluno.setBairro(body.bairro());
        if (body.cidade() != null) aluno.setCidade(body.cidade());
        if (body.UF() != null) aluno.setUF(body.UF());
        if (body.telefone() != null) aluno.setTelefone(body.telefone());
        if (body.sexo() != null) aluno.setSexo(body.sexo());
        if (body.data_nasc() != null) aluno.setData_nasc(body.data_nasc());
        if (body.matricula() != null) aluno.setMatricula(body.matricula());

        return this.repository.save(aluno);
    }

    @Override
    public AlunoDTO alterarSituacaoAluno(String id_aluno) {
        Aluno aluno = this.repository.findById(id_aluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado."));

        switch (aluno.getSituacao()) {
            case ATIVO -> aluno.setSituacao(SituacaoType.INATIVO);
            case INATIVO -> aluno.setSituacao(SituacaoType.ATIVO);
        }
        aluno.setData_alteracao(LocalDate.now());

        // Salva no banco
        Aluno atualizado = this.repository.save(aluno);

        return new AlunoDTO(atualizado);
    }

    @Override
    public AlunoDTO buscarAluno(String id_aluno){
        Aluno aluno = this.repository.findById(id_aluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        return new AlunoDTO(aluno);
    }

    @Override
    public CursoDTO buscarAlunoCurso(String id_aluno) {
        Aluno aluno = this.repository.findById(id_aluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado."));

        Curso curso = this.cursoRepository.findById(aluno.getTurma().getIdCurso())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        return new CursoDTO(curso);
    }

    @Override
    public List<SemestreAlunoDTO> buscarPeriodoAluno(String idAluno) {
        Aluno aluno = repository.findById(idAluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado."));

        String idGrade = aluno.getTurma().getGrade().getIdGrade();

        // usa o método que já faz JOIN FETCH
        List<Semestre> semestres = semestreRepository
                .findAllByGradeComDisciplinasEProfessores(idGrade);

        if (semestres.isEmpty()) {
            throw new NotFoundException("Semestres não encontrados para a grade.");
        }

        // faz o map para DTOs
        return semestres.stream()
                .map(s -> {
                    var listaSD = s.getSemestreDisciplinas().stream()
                            .map(sd -> {
                                // obtém nome da disciplina e do professor
                                String nomeDisc = sd.getDisciplina().getNome();
                                String nomeProf = sd.getProfessor().getNome();

                                var idDto = new IdSemestreDisciplinaDTO(
                                        s.getIdSemestre(),
                                        nomeDisc,
                                        nomeProf
                                );
                                return new SemestreDisciplinaDTO(
                                        idDto,
                                        sd.getDiaSemana(),
                                        sd.getStatus()
                                );
                            })
                            .toList();

                    return new SemestreAlunoDTO(
                            s.getIdSemestre(),
                            s.getNumero(),
                            s.getStatus(),
                            listaSD
                    );
                })
                .toList();
    }


}
