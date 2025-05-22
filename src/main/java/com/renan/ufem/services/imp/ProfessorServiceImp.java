package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.professor.ProfessorRegisterRequestDTO;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImp implements ProfessorService {
    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Professor criarProfessor(ProfessorRegisterRequestDTO dto, String idSecretaria) {
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
        newProfessor.setId_secretaria(idSecretaria);

        return this.repository.save(newProfessor);
    }
}
