package com.renan.ufem.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.renan.ufem.enums.SituacaoType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "professor")
@Data
public class Professor extends Pessoa implements UsuarioAutenticavel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor")
    private String idProfessor;

    @Column(name = "id_secretaria")
    private String idSecretaria;

    private LocalDate data_alteracao;

    @Enumerated(EnumType.STRING)
    private SituacaoType situacao;

    //@OneToMany(mappedBy = "semestre_disciplina", cascade = CascadeType.ALL)
    //@JsonManagedReference
    //private List<Semestre> semestres;

    @Override
    public String getLogin() {
        return this.getCPF(); // ou getEmail(), dependendo do login usado
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
        return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
