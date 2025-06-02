package com.renan.ufem.controllers;

import com.renan.ufem.domain.Professor;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.professor.ProfessorDTO;
import com.renan.ufem.dto.professor.ProfessorEditarDTO;
import com.renan.ufem.dto.professor.ProfessorLoginRequestDTO;
import com.renan.ufem.dto.professor.ProfessorResponseDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.services.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    final private JwtTokenService tokenService;
    final private ProfessorService professorService;

    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    @GetMapping("/{id_professor}")
    public ResponseEntity<ProfessorDTO> buscarProfessor(@PathVariable String id_professor) {
        ProfessorDTO professor = professorService.buscarProfessor(id_professor);
        return ResponseEntity.ok(professor);
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody ProfessorLoginRequestDTO body){
        try{
            Professor professor = professorService.loginProfessor(body);

            String token = this.tokenService.generateToken(professor);
            return ResponseEntity.ok(new ResponseDTO(professor.getIdProfessor(), token));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @PostMapping("/{id_secretaria}")
    public ResponseEntity criarProfessor(
            @RequestBody @Valid ProfessorDTO body,
            @PathVariable String id_secretaria
    ){
        try {
            professorService.criarProfessor(body, id_secretaria);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @DeleteMapping("/{id_professor}/situacao")
    public ResponseEntity<ProfessorDTO> alterarSituacao(@PathVariable String id_professor) {
        ProfessorDTO atualizado = professorService.alterarSituacaoProfessor(id_professor);
        return ResponseEntity.ok(atualizado);
    }

    @PreAuthorize("hasRole('SECRETARIA') or hasRole('PROFESSOR')")
    @PutMapping("/{id_professor}")
    public  ResponseEntity<ProfessorDTO> editarProfessor(@PathVariable String id_professor, @RequestBody @Valid ProfessorEditarDTO professor) {
        Professor professorAtualizado = professorService.editarProfessor(id_professor, professor);
        return ResponseEntity.ok(new ProfessorDTO(professorAtualizado));
    }

    @PreAuthorize("hasRole('SECRETARIA')")
    @GetMapping("/secretaria/{id_secretaria}")
    public ResponseEntity<List<ProfessorResponseDTO>> listarPorSecretaria(@PathVariable String id_secretaria) {
        return ResponseEntity.ok(professorService.buscarProfessorPorSecretaria(id_secretaria));
    }
}
