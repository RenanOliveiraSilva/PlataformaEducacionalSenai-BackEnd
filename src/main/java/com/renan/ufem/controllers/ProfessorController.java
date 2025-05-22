package com.renan.ufem.controllers;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.professor.ProfessorLoginRequestDTO;
import com.renan.ufem.dto.professor.ProfessorRegisterRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.ProfessorRepository;
import com.renan.ufem.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    final private ProfessorRepository repository;
    final private PasswordEncoder passwordEncoder;
    final private JwtTokenService tokenService;
    final private ProfessorService professorService;

    @GetMapping("/")
    public ResponseEntity<String> getProfessor() {
        return ResponseEntity.ok("sucesso!");
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody ProfessorLoginRequestDTO body){
        try{
            Professor professor = professorService.loginProfessor(body);

            String token = this.tokenService.generateToken(professor);
            return ResponseEntity.ok(new ResponseDTO(professor.getId_professor(), token));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_secretaria}/criarProfessor")
    public ResponseEntity criarProfessor(
            @RequestBody ProfessorRegisterRequestDTO body,
            @PathVariable String id_secretaria
    ){
        try {
            professorService.criarProfessor(body, id_secretaria);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
