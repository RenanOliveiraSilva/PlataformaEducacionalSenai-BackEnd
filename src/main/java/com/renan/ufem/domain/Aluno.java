package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "aluno")
@Data
public class Aluno extends Pessoa implements UsuarioAutenticavel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_aluno;
    private String matricula;

    @Override
    public String getLogin() {
        return this.getCPF(); // ou getEmail(), depende do login que você usa
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

