package com.renan.ufem.controllers;

import com.renan.ufem.dto.disciplina.DisciplinaDTO;
import com.renan.ufem.dto.disciplina.DisciplinaProfessorDTO;
import com.renan.ufem.dto.disciplina.DisciplinaResponseDTO;
import com.renan.ufem.services.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService service;

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_secretaria}")
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@PathVariable String id_secretaria, @RequestBody DisciplinaDTO body) {
        return ResponseEntity.ok(service.criar(id_secretaria, body));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PutMapping("/{id_disciplina}")
    public ResponseEntity<DisciplinaResponseDTO> editarDisciplina(@PathVariable String id_disciplina, @RequestBody DisciplinaDTO body) {
        return ResponseEntity.ok(service.editar(id_disciplina, body));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/{id_disciplina}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorIdDisciplina(@PathVariable String id_disciplina) {
        return ResponseEntity.ok(service.buscarPorId(id_disciplina));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/secretaria/{id_secretaria}")
    public ResponseEntity<List<DisciplinaResponseDTO>> buscarPorSecretariaDisciplina(@PathVariable String id_secretaria) {
        return ResponseEntity.ok(service.buscarPorSecretaria(id_secretaria));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @DeleteMapping("/{id_disciplina}/situacao")
    public ResponseEntity<DisciplinaResponseDTO> alterarSituacaoDisciplina(@PathVariable String id_disciplina) {
        return ResponseEntity.ok(service.alterarSituacao(id_disciplina));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/professor/{id_turma}/{id_professor}")
    public ResponseEntity<List<DisciplinaProfessorDTO>> listarDisciplinasPorProfessorETurma(
            @PathVariable String id_turma,
            @PathVariable String id_professor
    ) {
        List<DisciplinaProfessorDTO> disciplinas = service.buscarDisciplinasPorTurmaEProfessor(id_turma, id_professor);
        return ResponseEntity.ok(disciplinas);
    }
}