package com.renan.ufem.controllers;

import com.renan.ufem.domain.Aluno;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.dto.aluno.AlunoUpdateDTO;
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
    public ResponseEntity<Void> criarAluno(
            @RequestBody @Valid AlunoDTO body,
            @PathVariable String id_turma
    ) {
        alunoService.criarAluno(body, id_turma);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SECRETARIA') or hasRole('ALUNO')")
    @PutMapping("/editar/{id_aluno}")
    public ResponseEntity<AlunoDTO> editarAluno(
            @PathVariable String id_aluno,
            @RequestBody @Valid AlunoUpdateDTO body
    ) {
        Aluno alunoAtualizado = alunoService.editarAluno(body, id_aluno);
        return ResponseEntity.ok(new AlunoDTO(alunoAtualizado));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PutMapping("/situacao/{id_aluno}")
    public ResponseEntity<AlunoDTO> alterarSituacaoAluno(@PathVariable String id_aluno) {
        AlunoDTO atualizado = alunoService.alterarSituacaoAluno(id_aluno);
        return ResponseEntity.ok(atualizado);
    }

    @PreAuthorize("hasRole('SECRETARIA') or hasRole('ALUNO')")
    @GetMapping("/{id_aluno}")
    public ResponseEntity<AlunoDTO> buscarAluno(@PathVariable String id_aluno) {
        AlunoDTO aluno = alunoService.buscarAluno(id_aluno);
        return ResponseEntity.ok(aluno);
    }

}
