package com.renan.ufem.controllers.professor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class ProfessorController {
    @GetMapping("/")
    public ResponseEntity<String> getProfessor() {
        return ResponseEntity.ok("sucesso!");
    }

}
