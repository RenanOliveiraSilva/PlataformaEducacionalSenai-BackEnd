package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.professor.ProfessorDTO;
import com.renan.ufem.dto.professor.ProfessorEditarDTO;
import com.renan.ufem.dto.professor.ProfessorLoginRequestDTO;
import com.renan.ufem.dto.professor.ProfessorResponseDTO;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.renan.ufem.enums.SituacaoType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImp implements ProfessorService {
    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Professor criarProfessor(ProfessorDTO dto, String idSecretaria) {
        Optional<Professor> professorExiste = this.repository.findByCPF(dto.CPF());

        if(professorExiste.isPresent()) {
            throw new RuntimeException("Professor já cadastrado");
        }

        Professor newProfessor = new Professor();
        newProfessor.setSenha(passwordEncoder.encode(dto.senha()));
        newProfessor.setEmail(dto.email());
        newProfessor.setNome(dto.nome());
        newProfessor.setCPF(dto.CPF());
        newProfessor.setTelefone(dto.telefone());
        newProfessor.setLogradouro(dto.logradouro());
        newProfessor.setBairro(dto.bairro());
        newProfessor.setNumero(dto.numero());
        newProfessor.setCidade(dto.cidade());
        newProfessor.setUF(dto.UF());
        newProfessor.setSexo(dto.sexo());
        newProfessor.setData_nasc(dto.data_nasc());
        newProfessor.setSituacao(SituacaoType.ATIVO);
        newProfessor.setIdSecretaria(idSecretaria);

        return this.repository.save(newProfessor);
    }

    public Professor loginProfessor(ProfessorLoginRequestDTO body){
        Professor professor = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Login inválido."));

        if (passwordEncoder.matches(body.senha(), professor.getSenha())){
            return professor;
        };

        throw new RuntimeException("Login inválido.");
    }

    @Override
    public ProfessorDTO buscarProfessor(String id_professor) {
        Professor professor = this.repository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado."));

        return new ProfessorDTO(professor);
    }

    @Override
    public ProfessorDTO alterarSituacaoProfessor(String id_professor) {
        Professor professor = this.repository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado."));

        switch (professor.getSituacao()) {
            case ATIVO -> professor.setSituacao(SituacaoType.INATIVO);
            case INATIVO -> professor.setSituacao(SituacaoType.ATIVO);
        }
        professor.setData_alteracao(LocalDate.now());

        // Salva no banco
        Professor atualizado = this.repository.save(professor);

        return new ProfessorDTO(atualizado);
    }

    @Override
    public Professor editarProfessor(String id_professor, ProfessorEditarDTO body) {
        Professor professor = this.repository.findById(id_professor)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado."));

        if(body.email() != null && !body.email().equals(professor.getEmail())){
            this.repository.findByEmail(body.email()).ifPresent(e -> {
                throw new ConflictException("E-mail já está em uso.");
            });
            professor.setEmail(body.email());
        };

        if(body.CPF() != null && !body.CPF().equals(professor.getCPF())){
            this.repository.findByCPF(body.CPF()).ifPresent(e -> {
                throw new ConflictException("CPF já está em uso.");
            });
            professor.setCPF(body.CPF());
        };

        if (body.nome() != null) professor.setNome(body.nome());
        if (body.telefone() != null) professor.setTelefone(body.telefone());
        if (body.UF() != null) professor.setUF(body.UF());
        if (body.cidade() != null) professor.setCidade(body.cidade());
        if (body.bairro() != null) professor.setBairro(body.bairro());
        if (body.logradouro() != null) professor.setLogradouro(body.logradouro());
        if (body.numero() != null) professor.setNumero(body.numero());
        if (body.situacao() != null) professor.setSituacao(body.situacao());
        if (body.sexo() != null) professor.setSexo(body.sexo());
        if (body.data_nasc() != null) professor.setData_nasc(body.data_nasc());

        if (body.senha() != null && !body.senha().isBlank()) {
            professor.setSenha(passwordEncoder.encode(body.senha()));
        }

        return this.repository.save(professor);

    }

    @Override
    public List<ProfessorResponseDTO> buscarProfessorPorSecretaria(String id_secretaria) {
        return repository.findByIdSecretaria(id_secretaria)
                .stream()
                .map(ProfessorResponseDTO::new)
                .toList();
    }
}
