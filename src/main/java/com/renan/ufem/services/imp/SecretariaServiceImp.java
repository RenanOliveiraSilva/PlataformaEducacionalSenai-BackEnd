package com.renan.ufem.services.imp;

import com.renan.ufem.domain.Secretaria;
import com.renan.ufem.dto.secretaria.SecretariaDTO;
import com.renan.ufem.dto.secretaria.SecretariaLoginRequestDTO;
import com.renan.ufem.dto.secretaria.SecretariaUpdateDTO;
import com.renan.ufem.exceptions.ConflictException;
import com.renan.ufem.exceptions.NotFoundException;
import com.renan.ufem.exceptions.UnauthorizedException;
import com.renan.ufem.infra.security.JwtTokenService;
import com.renan.ufem.repositories.SecretariaRepository;
import com.renan.ufem.services.ProfessorService;
import com.renan.ufem.services.SecretariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretariaServiceImp implements SecretariaService {
    private final SecretariaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private JwtTokenService tokenService;

    @Override
    public SecretariaDTO getSecretaria(String id_secretaria) {
        Secretaria secretaria = repository.findById(id_secretaria)
                .orElseThrow(() -> new NotFoundException("Secretaria não encontrada."));
        return new SecretariaDTO(secretaria);
    }

    @Override
    public Secretaria loginSecretaria(SecretariaLoginRequestDTO body) {
        Secretaria secretaria = repository.findByEmail(body.email())
                .orElseThrow(() -> new NotFoundException("E-mail não encontrado."));

        if (!passwordEncoder.matches(body.senha(), secretaria.getSenha())) {
            throw new UnauthorizedException("Senha incorreta.");
        }

        return secretaria;
    }

    @Override
    public Secretaria criarSecretaria(SecretariaDTO body) {
        if (repository.findByEmail(body.email()).isPresent()) {
            throw new ConflictException("E-mail já está em uso.");
        }

        Secretaria newSecretaria = converterDTO(body);
        return repository.save(newSecretaria);
    }

    @Override
    public Secretaria atualizarSecretaria(SecretariaUpdateDTO body, String id_secretaria) {
        Secretaria secretaria = this.repository.findById(id_secretaria)
                .orElseThrow(() -> new NotFoundException("Secretaria não encontrada."));

        if (body.email() != null && !body.email().equals(secretaria.getEmail())) {
            this.repository.findByEmail(body.email()).ifPresent(e -> {
                throw new ConflictException("E-mail já está em uso.");
            });
            secretaria.setEmail(body.email());
        }

        if (body.nome() != null) secretaria.setNome(body.nome());
        if (body.telefone() != null) secretaria.setTelefone(body.telefone());
        if (body.UF() != null) secretaria.setUF(body.UF());
        if (body.cidade() != null) secretaria.setCidade(body.cidade());
        if (body.bairro() != null) secretaria.setBairro(body.bairro());
        if (body.logradouro() != null) secretaria.setLogradouro(body.logradouro());
        if (body.numero() != null) secretaria.setNumero(body.numero());

        if (body.senha() != null && !body.senha().isBlank()) {
            secretaria.setSenha(passwordEncoder.encode(body.senha()));
        }

        return repository.save(secretaria);
    }

    public Secretaria converterDTO(SecretariaDTO DTO) {
        Secretaria secretaria = new Secretaria();
        secretaria.setNome(DTO.nome());
        secretaria.setEmail(DTO.email());
        secretaria.setSenha(passwordEncoder.encode(DTO.senha()));
        secretaria.setUF(DTO.UF());
        secretaria.setCidade(DTO.cidade());
        secretaria.setBairro(DTO.bairro());
        secretaria.setLogradouro(DTO.logradouro());
        secretaria.setNumero(DTO.numero());
        secretaria.setTelefone(DTO.telefone());

        return secretaria;
    }

}
