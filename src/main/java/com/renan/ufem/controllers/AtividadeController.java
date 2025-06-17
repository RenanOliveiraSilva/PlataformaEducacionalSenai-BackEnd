package com.renan.ufem.controllers;

import com.renan.ufem.domain.Atividade;
import com.renan.ufem.dto.atividade.AtividadeCreateDTO;
import com.renan.ufem.dto.atividade.AtividadeResponseDTO;
import com.renan.ufem.dto.atividade.AvaliacaoDTO;
import com.renan.ufem.dto.atividade.ConcluirAtividadeDTO;
import com.renan.ufem.services.AtividadeService;
import jakarta.validation.Valid;
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

    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
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

    @PreAuthorize("hasRole('ALUNO')")
    @PostMapping("/{idAtividade}/aluno/{idAluno}/concluir")
    public ResponseEntity<Void> concluir(
            @PathVariable String idAtividade,
            @PathVariable String idAluno
    ) {
        service.concluirAtividade(idAluno, idAtividade);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ALUNO')")
    @GetMapping("/aluno/{idAluno}")
    public List<AtividadeResponseDTO> getByAluno(@PathVariable String idAluno) {
        return this.service.buscarAtividadesPorAluno(idAluno);
    }

    @PatchMapping("/avaliar/{idAtividade}/{idAluno}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<Void> avaliarAtividade(
            @PathVariable String idAtividade,
            @PathVariable String idAluno,
            @RequestBody @Valid AvaliacaoDTO dto
    ) {
        service.avaliarAtividade(idAtividade, idAluno, dto.nota());
        return ResponseEntity.noContent().build();
    }

}