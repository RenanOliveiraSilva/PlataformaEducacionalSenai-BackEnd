package com.renan.ufem.controllers.aluno;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @GetMapping("/")
    public ResponseEntity<String> getAluno() {
        return ResponseEntity.ok("sucesso!");
    }
}
