package com.renan.ufem.controllers;

import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaRequestDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaResponseDTO;
import com.renan.ufem.services.SemestreDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/semestre-disciplina")
@RequiredArgsConstructor
public class SemestreDisciplinaController {

    private final SemestreDisciplinaService service;

    @PostMapping
    public ResponseEntity<SemestreDisciplinaResponseDTO> criar(@RequestBody SemestreDisciplinaRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<SemestreDisciplinaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemestreDisciplinaResponseDTO> buscar(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

}
