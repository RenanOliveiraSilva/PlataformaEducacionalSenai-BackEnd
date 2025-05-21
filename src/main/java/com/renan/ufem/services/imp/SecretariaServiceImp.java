package com.renan.ufem.services.imp;


import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.ResponseDTO;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.SecretariaRepository;
import com.renan.ufem.services.SecretariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecretariaServiceImp implements SecretariaService {
    private final SecretariaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private JwtTokenService tokenService;

    @Override
    public Secretaria criarSecretaria(Secretaria secretaria){
        Optional<Secretaria> existeSecretaria = this.repository.findByEmail(secretaria.getEmail());

        if (existeSecretaria.isPresent()) {
            throw new RuntimeException("E-mail já está em uso."); // ou uma exceção customizada
        }

        Secretaria newSecretaria = new Secretaria();
        newSecretaria.setSenha(passwordEncoder.encode(secretaria.getSenha()));
        newSecretaria.setEmail(secretaria.getEmail());
        newSecretaria.setNome(secretaria.getNome());
        newSecretaria.setTelefone(secretaria.getTelefone());
        newSecretaria.setLogradouro(secretaria.getLogradouro());
        newSecretaria.setBairro(secretaria.getBairro());
        newSecretaria.setNumero(secretaria.getNumero());
        newSecretaria.setCidade(secretaria.getCidade());
        newSecretaria.setUF(secretaria.getUF());

        return repository.save(newSecretaria);

    }

    @Override
    public Secretaria loginSecretaria(Secretaria dto){
        Secretaria secretaria = this.repository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Login inválido."));

        if(passwordEncoder.matches(dto.getSenha(), secretaria.getSenha())) {
            return secretaria;

        }

        throw new RuntimeException("Login inválido.");
    }

}
