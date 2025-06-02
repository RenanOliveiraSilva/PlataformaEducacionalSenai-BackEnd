package com.renan.ufem.controllers;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.dto.turma.TurmaResponseDTO;
import com.renan.ufem.services.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/criar/{id_secretaria}/{id_curso}")
    public ResponseEntity<Turma> criarTurma(
            @PathVariable String id_secretaria,
            @PathVariable String id_curso,
            @RequestBody @Valid TurmaDTO body
    ) {
        Turma novaTurma = turmaService.criarTurma(id_secretaria, id_curso, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/buscarTurma/{id_turma}")
    public ResponseEntity<TurmaResponseDTO> buscarTurma(@PathVariable String id_turma) {
        TurmaResponseDTO turma = turmaService.buscarTurma(id_turma);
        return ResponseEntity.ok(turma);
    }

}
