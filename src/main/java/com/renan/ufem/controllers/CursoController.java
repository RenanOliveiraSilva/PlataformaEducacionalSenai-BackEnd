package com.renan.ufem.controllers;

import com.renan.ufem.domain.Curso;
import com.renan.ufem.dto.curso.CursoDTO;
import com.renan.ufem.dto.curso.CursoEditarDTO;
import com.renan.ufem.dto.curso.CursoResponseDTO;
import com.renan.ufem.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {

    final private CursoService cursoService;

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_secretaria}")
    public ResponseEntity<CursoResponseDTO> criarCurso(@PathVariable String id_secretaria, @RequestBody CursoDTO body) {
        Curso curso = this.cursoService.criarCurso(id_secretaria, body);
        return ResponseEntity.ok(new CursoResponseDTO(curso.getIdCurso()));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PutMapping("/{id_curso}")
    public ResponseEntity<Curso> editarCurso(@PathVariable String id_curso, @RequestBody CursoEditarDTO body) {
        Curso atualizado = this.cursoService.editarCurso(id_curso, body);
        return ResponseEntity.ok(atualizado);
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PutMapping("/{id_curso}/situacao")
    public ResponseEntity<CursoDTO> alterarSituacaoCurso(@PathVariable String id_curso) {
        CursoDTO atualizado = this.cursoService.alterarSituacaoCurso(id_curso);
        return ResponseEntity.ok(atualizado);
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/{id_curso}")
    public ResponseEntity<CursoDTO> buscarCurso(@PathVariable String id_curso) {
        CursoDTO curso = this.cursoService.buscarCurso(id_curso);

        return ResponseEntity.ok(curso);
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/{id_secretaria}/secretaria")
    public ResponseEntity<List<CursoDTO>> buscarCursosPorSecretaria(@PathVariable String id_secretaria) {
        List<CursoDTO> cursos = this.cursoService.buscarCursoPorSecretaria(id_secretaria);
        return ResponseEntity.ok(cursos);
    }


}

