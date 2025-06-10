package com.renan.ufem.controllers;

import com.renan.ufem.domain.Atividade;
import com.renan.ufem.dto.atividade.AtividadeCreateDTO;
import com.renan.ufem.dto.atividade.AtividadeResponseDTO;
import com.renan.ufem.services.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
@RequiredArgsConstructor
public class AtividadeController {
    private final AtividadeService service;

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_disciplina}/{id_turma}/{id_professor}")
    public ResponseEntity<AtividadeResponseDTO> criar(
            @RequestBody AtividadeCreateDTO atividade,
            @PathVariable String id_disciplina,
            @PathVariable String id_turma,
            @PathVariable String id_professor
    ) {
        AtividadeResponseDTO newAtividade = this.service.criarAtividade(id_disciplina, id_turma, id_professor, atividade);
        return ResponseEntity.ok(newAtividade);
    }


}