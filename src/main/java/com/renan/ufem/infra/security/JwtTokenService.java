package com.renan.ufem.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.renan.ufem.domain.UsuarioAutenticavel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioAutenticavel usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ufem")
                    .withSubject(usuario.getLogin()) // pode ser CPF ou Email, dependendo da classe
                    .withClaim("role", usuario.getAuthorities().stream().findFirst().get().getAuthority())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ufem")
                    .build()
                    .verify(token)
                    .getSubject(); // retorna o login (CPF ou email) para usar no UserDetailsService
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
