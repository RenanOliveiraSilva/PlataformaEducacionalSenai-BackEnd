package com.renan.ufem.controllers;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.AlunoRepository;
import com.renan.ufem.services.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;
    private final AlunoService alunoService;

    @GetMapping("/")
    public ResponseEntity<String> getAluno() {
        return ResponseEntity.ok("sucesso!");
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody AlunoLoginRequestDTO body){
        try{
            Aluno aluno = alunoService.loginAluno(body);

            String token = this.tokenService.generateToken(aluno);
            return ResponseEntity.ok(new ResponseDTO(aluno.getIdAluno(), token));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/criarAluno/{id_turma}")
    public ResponseEntity criarAluno(
            @RequestBody @Valid AlunoDTO body,
            @PathVariable String id_turma
    ) {
        try {
            alunoService.criarAluno(body, id_turma);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
