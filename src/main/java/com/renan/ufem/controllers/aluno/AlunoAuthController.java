package com.renan.ufem.controllers.aluno;

import com.renan.ufem.domain.aluno.Aluno;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.dto.aluno.AlunoRegisterRequestDTO;
import com.renan.ufem.infra.security.aluno.AlunoTokenService;
import com.renan.ufem.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/aluno/auth")
@RequiredArgsConstructor
public class AlunoAuthController {

    private final AlunoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AlunoTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AlunoLoginRequestDTO body){
        Aluno aluno = this.repository.findByCPF(body.CPF()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.senha(), aluno.getSenha())) {
            String token = this.tokenService.generateToken(aluno);
            return ResponseEntity.ok(new ResponseDTO(aluno.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody AlunoRegisterRequestDTO body){
        Optional<Aluno> aluno = this.repository.findByCPF(body.CPF());

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

            this.repository.save(newAluno);

            String token = this.tokenService.generateToken(newAluno);
            return ResponseEntity.ok(new ResponseDTO(newAluno.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
