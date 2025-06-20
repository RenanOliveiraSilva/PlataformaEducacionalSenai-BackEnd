package com.renan.ufem.controllers;

import com.renan.ufem.dto.aula.RegistroChamadaDTO;
import com.renan.ufem.services.ChamadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final ChamadaService aulaService;

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarAula(@RequestBody RegistroChamadaDTO dto) {
        aulaService.registrarChamada(dto);
        return ResponseEntity.ok().build();
    }
}
