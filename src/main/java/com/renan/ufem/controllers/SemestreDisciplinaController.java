package com.renan.ufem.controllers;

import com.renan.ufem.domain.SemestreDisciplina;
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
    public ResponseEntity<SemestreDisciplina> criarVinculo(
            @RequestParam String idSemestre,
            @RequestParam String idDisciplina,
            @RequestParam String idProfessor,
            @RequestParam String diaSemana) {

        SemestreDisciplina sd = service.salvar(idSemestre, idDisciplina, idProfessor, diaSemana);
        return ResponseEntity.ok(sd);
    }
}

