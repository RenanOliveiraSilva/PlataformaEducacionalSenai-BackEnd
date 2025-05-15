package com.renan.ufem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secretaria")
public class SecretariaController {

    @GetMapping
    public ResponseEntity<String> getSecretaria() {
        return ResponseEntity.ok("sucesso!");
    }
}
