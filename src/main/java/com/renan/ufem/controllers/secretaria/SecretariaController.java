package com.renan.ufem.controllers.secretaria;

import com.renan.ufem.domain.aluno.Aluno;
import com.renan.ufem.domain.professor.Professor;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoRegisterRequestDTO;
import com.renan.ufem.dto.professor.ProfessorRegisterRequestDTO;
import com.renan.ufem.infra.security.secretaria.SecretariaTokenService;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaController {

    @Autowired
    private SecretariaTokenService tokenService;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/")
    public ResponseEntity<String> getSecretaria() {
        return ResponseEntity.ok("sucesso!");
    }

    @PostMapping("/{id_secretaria}/criarProfessor")
    public ResponseEntity criarProfessor(
            @RequestBody ProfessorRegisterRequestDTO body,
            @PathVariable String id_secretaria
    ){
        Optional<Professor> professor = this.professorRepository.findByCPF(body.CPF());

        if (professor.isEmpty()) {
            Professor newProfessor = new Professor();
            newProfessor.setSenha(passwordEncoder.encode(body.senha()));
            newProfessor.setEmail(body.email());
            newProfessor.setNome(body.nome());
            newProfessor.setCPF(body.CPF());
            newProfessor.setTelefone(body.telefone());
            newProfessor.setLogradouro(body.logradouro());
            newProfessor.setBairro(body.bairro());
            newProfessor.setNumero(body.numero());
            newProfessor.setCidade(body.cidade());
            newProfessor.setUF(body.UF());
            newProfessor.setSexo(body.sexo());
            newProfessor.setData_nasc(body.data_nasc());
            newProfessor.setId_secretaria(id_secretaria);

            this.professorRepository.save(newProfessor);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id_secretaria}/register")
    public ResponseEntity criarAluno(@RequestBody AlunoRegisterRequestDTO body){

        Optional<Aluno> aluno = this.alunoRepository.findByCPF(body.CPF());

        if (aluno.isEmpty()) {
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

            this.alunoRepository.save(newAluno);

            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.badRequest().build();
    }
}
