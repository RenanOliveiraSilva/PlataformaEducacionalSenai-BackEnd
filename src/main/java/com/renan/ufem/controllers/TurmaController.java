package com.renan.ufem.controllers;

import com.renan.ufem.domain.Turma;
import com.renan.ufem.dto.turma.TurmaDTO;
import com.renan.ufem.services.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping("/criar/{id_secretaria}/{id_curso}")
    public ResponseEntity<Turma> criarTurma(
            @PathVariable String id_secretaria,
            @PathVariable String id_curso,
            @RequestBody @Valid TurmaDTO body
    ) {
        Turma novaTurma = turmaService.criarTurma(id_secretaria, id_curso, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }


}
