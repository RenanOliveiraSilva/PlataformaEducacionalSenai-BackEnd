package com.renan.ufem.controllers;

import com.renan.ufem.domain.SemestreDisciplina;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaDTO;
import com.renan.ufem.dto.semestreDisciplina.SemestreDisciplinaRequestDTO;
import com.renan.ufem.enums.DiaSemana;
import com.renan.ufem.services.SemestreDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semestre-disciplina")
@RequiredArgsConstructor
public class SemestreDisciplinaController {

    private final SemestreDisciplinaService service;

    @PostMapping
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SemestreDisciplina> criarVinculo(
            @RequestParam String idSemestre,
            @RequestParam String idDisciplina,
            @RequestParam String idProfessor,
            @RequestBody SemestreDisciplinaRequestDTO body) {

        SemestreDisciplina sd = service.salvar(idSemestre, idDisciplina, idProfessor, body);
        return ResponseEntity.ok(sd);
    }
}

