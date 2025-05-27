package com.renan.ufem.controllers;

import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.secretaria.SecretariaDTO;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.dto.secretaria.SecretariaUpdateDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.services.SecretariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaController {

    private final JwtTokenService tokenService;
    private final SecretariaService secretariaService;

    @GetMapping("/{id_secretaria}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SecretariaDTO> buscarSecretaria(@PathVariable String id_secretaria) {
        SecretariaDTO secretaria = secretariaService.getSecretaria(id_secretaria);
        return ResponseEntity.ok(secretaria);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody SecretariaLoginRequestDTO body) {
        Secretaria secretaria = secretariaService.loginSecretaria(body);
        String token = tokenService.generateToken(secretaria);
        return ResponseEntity.ok(new ResponseDTO(secretaria.getIdSecretaria(), token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ResponseDTO> criarSecretaria(@RequestBody @Valid SecretariaDTO body) {
        Secretaria newSecretaria = secretariaService.criarSecretaria(body);
        String token = tokenService.generateToken(newSecretaria);
        return ResponseEntity.ok(new ResponseDTO(newSecretaria.getIdSecretaria(), token));
    }

    @DeleteMapping("/{id_secretaria}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<SecretariaDTO> atualizarSecretaria(
            @RequestBody @Valid SecretariaUpdateDTO body,
            @PathVariable String id_secretaria
    ) {
        Secretaria secretariaAtualizada = secretariaService.atualizarSecretaria(body, id_secretaria);
        return ResponseEntity.ok(new SecretariaDTO(secretariaAtualizada));
    }
}
