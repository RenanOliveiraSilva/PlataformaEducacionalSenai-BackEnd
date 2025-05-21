package com.renan.ufem.controllers.professor;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.professor.ProfessorLoginRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor/auth")
@RequiredArgsConstructor
public class ProfessorAuthController {

    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody ProfessorLoginRequestDTO body){
        Professor professor = this.repository.findByCPF(body.CPF()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.senha(), professor.getSenha())) {
            String token = this.tokenService.generateToken(professor);
            return ResponseEntity.ok(new ResponseDTO(professor.getId_professor(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
