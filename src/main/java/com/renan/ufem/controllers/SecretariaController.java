package com.renan.ufem.controllers;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.domain.Professor;
import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoRegisterRequestDTO;
import com.renan.ufem.dto.professor.ProfessorRegisterRequestDTO;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.repositories.SecretariaRepository;
import com.renan.ufem.services.ProfessorService;
import com.renan.ufem.services.SecretariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaController {

    @Autowired
    private JwtTokenService tokenService;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfessorRepository professorRepository;

    private final SecretariaService secretariaService;
    private final SecretariaRepository secretariaRepository;
    private final ProfessorService professorService;

    @GetMapping("/")
    public ResponseEntity<String> getSecretaria() {
        return ResponseEntity.ok("sucesso!");
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_secretaria}/criarProfessor")
    public ResponseEntity criarProfessor(
            @RequestBody ProfessorRegisterRequestDTO body,
            @PathVariable String id_secretaria
    ){
        try {
            // Delega a lógica para o ProfessorService
            professorService.criarProfessor(body, id_secretaria);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody SecretariaLoginRequestDTO body){
        try {
            Secretaria secretaria = secretariaService.loginSecretaria(body);
            String token = this.tokenService.generateToken(secretaria);
            return ResponseEntity.ok(new ResponseDTO(secretaria.getId_secretaria(), token));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> criarSecretaria(@RequestBody Secretaria body) {
        try {
            Secretaria newSecretaria = secretariaService.criarSecretaria(body);
            String token = this.tokenService.generateToken(newSecretaria);
            return ResponseEntity.ok(new ResponseDTO(newSecretaria.getId_secretaria(), token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Secretaria converterDTO(Secretaria dto) {
        Secretaria secretaria = new Secretaria();
        secretaria.setNome(dto.getNome());
        secretaria.setEmail(dto.getEmail());
        secretaria.setSenha(dto.getSenha());
        secretaria.setTelefone(dto.getTelefone());
        secretaria.setUF(dto.getUF());
        secretaria.setCidade(dto.getCidade());
        secretaria.setBairro(dto.getBairro());
        secretaria.setLogradouro(dto.getLogradouro());
        secretaria.setNumero(dto.getNumero());

        return secretaria;
    }

}
