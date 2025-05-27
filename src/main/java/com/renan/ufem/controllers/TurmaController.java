package com.renan.ufem.controllers;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.dto.turma.TurmaResponseDTO;
import com.renan.ufem.services.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> criarTurma(@RequestBody TurmaDTO body) {
        Turma turma = turmaService.criarTurma(body);
        return ResponseEntity.ok(new TurmaResponseDTO(turma.getIdTurma()));
    }
}
