package com.renan.ufem.controllers;

import com.renan.ufem.domain.Grade;
import com.renan.ufem.dto.grade.GradeDTO;
import com.renan.ufem.services.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService service;

    @PostMapping
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<Grade> criar(@RequestBody @Valid GradeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarGrade(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<List<Grade>> listar() {
        return ResponseEntity.ok(service.listarGrades());
    }
}
