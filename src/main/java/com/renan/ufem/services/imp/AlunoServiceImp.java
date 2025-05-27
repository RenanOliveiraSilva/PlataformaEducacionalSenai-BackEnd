package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.enums.SituacaoType;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.repositories.TurmaRepository;
import com.renan.ufem.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoServiceImp implements AlunoService {
    private final AlunoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TurmaRepository turmaRepository;

    @Override
    public Aluno loginAluno(AlunoLoginRequestDTO body) {

        Aluno aluno = this.repository.findByCPF(body.CPF()).orElseThrow(() -> new RuntimeException("Aluno not found"));

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

}
