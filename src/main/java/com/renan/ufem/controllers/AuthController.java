package com.renan.ufem.controllers;

import com.renan.ufem.domain.secretaria.Secretaria;
import com.renan.ufem.dto.LoginRequestDTO;
import com.renan.ufem.infra.security.TokenService;
import com.renan.ufem.repositories.SecretariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecretariaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    /*@PostMapping("/login")
    public ResponseEntity login(@ResponseBody LoginRequestDTO body){
        Secretaria secretaria
    }*/
}
