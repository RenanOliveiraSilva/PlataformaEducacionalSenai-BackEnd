package com.renan.ufem.controllers.aluno;

import com.renan.ufem.domain.Aluno;

import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.aluno.AlunoLoginRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aluno/auth")
@RequiredArgsConstructor
public class AlunoAuthController {

    private final AlunoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AlunoLoginRequestDTO body){
        Aluno aluno = this.repository.findByCPF(body.CPF()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.senha(), aluno.getSenha())) {
            String token = this.tokenService.generateToken(aluno);
            return ResponseEntity.ok(new ResponseDTO(aluno.getId_aluno(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
