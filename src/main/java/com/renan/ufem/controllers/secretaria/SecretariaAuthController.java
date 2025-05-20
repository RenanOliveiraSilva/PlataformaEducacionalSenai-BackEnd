package com.renan.ufem.controllers.secretaria;

import com.renan.ufem.domain.secretaria.Secretaria;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.dto.secretaria.SecretariaRegisterRequestDTO;
import com.renan.ufem.infra.security.secretaria.SecretariaTokenService;
import com.renan.ufem.repositories.SecretariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/secretaria/auth")
@RequiredArgsConstructor
public class SecretariaAuthController {

    private final SecretariaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final SecretariaTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecretariaLoginRequestDTO body){
        Secretaria secretaria = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.senha(), secretaria.getSenha())) {
            String token = this.tokenService.generateToken(secretaria);
            return ResponseEntity.ok(new ResponseDTO(secretaria.getId_secretaria(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody SecretariaRegisterRequestDTO body){
        Optional<Secretaria> secretaria = this.repository.findByEmail(body.email());

        if (secretaria.isEmpty()) {
            Secretaria newSecretaria = new Secretaria();
            newSecretaria.setSenha(passwordEncoder.encode(body.senha()));
            newSecretaria.setEmail(body.email());
            newSecretaria.setNome(body.nome());
            newSecretaria.setTelefone(body.telefone());
            newSecretaria.setLogradouro(body.logradouro());
            newSecretaria.setBairro(body.bairro());
            newSecretaria.setNumero(body.numero());
            newSecretaria.setCidade(body.cidade());
            newSecretaria.setUF(body.UF());

            this.repository.save(newSecretaria);

            String token = this.tokenService.generateToken(newSecretaria);
            return ResponseEntity.ok(new ResponseDTO(newSecretaria.getId_secretaria(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
