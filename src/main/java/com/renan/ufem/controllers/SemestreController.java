package com.renan.ufem.controllers;

import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.services.SemestreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semestres")
@RequiredArgsConstructor
public class SemestreController {
    private final SemestreService service;

    @PostMapping
    public ResponseEntity<Semestre> criar(@RequestBody @Valid SemestreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarSemestre(dto));
    }

    @GetMapping("/por-grade/{idGrade}")
    public ResponseEntity<List<Semestre>> listarPorGrade(@PathVariable String idGrade) {
        return ResponseEntity.ok(service.listarPorGrade(idGrade));
    }
}
