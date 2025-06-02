package com.renan.ufem.controllers;

import com.renan.ufem.domain.Semestre;
import com.renan.ufem.dto.semestre.SemestreDTO;
import com.renan.ufem.services.SemestreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semestre")
@RequiredArgsConstructor
public class SemestreController {
    private final SemestreService service;


    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping
    public ResponseEntity<Semestre> criar(@RequestBody @Valid SemestreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarSemestre(dto));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/grade/{idGrade}")
    public ResponseEntity<List<Semestre>> listarPorGrade(@PathVariable String idGrade) {
        return ResponseEntity.ok(service.listarPorGrade(idGrade));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PutMapping("/alterarSituacao/{id_semestre}")
    public ResponseEntity<Semestre> alterarSituacao(@PathVariable String id_semestre) {
        return ResponseEntity.ok(service.alterarSituacaoSemestre(id_semestre));
    }
}
