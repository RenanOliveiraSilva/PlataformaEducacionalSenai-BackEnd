package com.renan.ufem.controllers;

import com.renan.ufem.domain.secretaria.Secretaria;
import com.renan.ufem.dto.LoginRequestDTO;
import com.renan.ufem.dto.RegisterRequestDTO;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.infra.security.TokenService;
import com.renan.ufem.repositories.SecretariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecretariaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Secretaria secretaria = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.senha(), secretaria.getSenha())) {
            String token = this.tokenService.generateToken(secretaria);
            return ResponseEntity.ok(new ResponseDTO(secretaria.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody RegisterRequestDTO body){
        Optional<Secretaria> secretaria = this.repository.findByEmail(body.email());

        if(secretaria.isEmpty()) {
            Secretaria newSecretaria = new Secretaria();
            newSecretaria.setSenha(passwordEncoder.encode(body.senha()));
            newSecretaria.setEmail(body.email());
            newSecretaria.setNome(body.nome());
            newSecretaria.setTelefone(body.telefone());
            this.repository.save(newSecretaria);

            String token = this.tokenService.generateToken((newSecretaria));
            return ResponseEntity.ok(new ResponseDTO(newSecretaria.getNome(), token));

        }

        return ResponseEntity.badRequest().build();
    }
}
