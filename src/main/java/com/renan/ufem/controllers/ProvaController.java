package com.renan.ufem.controllers;

import com.renan.ufem.dto.prova.ProvaCreateDTO;
import com.renan.ufem.dto.prova.ProvaResponseDTO;
import com.renan.ufem.services.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provas")
@RequiredArgsConstructor
public class ProvaController {
    private final ProvaService service;

    @PostMapping("/{idTurma}/{idDisciplina}")
    public ResponseEntity<ProvaResponseDTO> criar(
            @PathVariable String idTurma,
            @PathVariable String idDisciplina,
            @RequestBody ProvaCreateDTO body
    ) {
        return ResponseEntity.ok(service.criarProva(idTurma, idDisciplina, body));
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<ProvaResponseDTO>> provasAluno(@PathVariable String idAluno) {
        return ResponseEntity.ok(service.buscarProvasPorAluno(idAluno));
    }

    @PostMapping("/concluir/{idProva}/{idAluno}")
    public ResponseEntity<Void> concluir(@PathVariable String idProva, @PathVariable String idAluno) {
        service.concluirProva(idAluno, idProva);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/avaliar/{idProva}/{idAluno}")
    public ResponseEntity<Void> avaliar(@PathVariable String idProva, @PathVariable String idAluno, @RequestBody Float nota) {
        service.avaliarProva(idAluno, idProva, nota);
        return ResponseEntity.ok().build();
    }
}
