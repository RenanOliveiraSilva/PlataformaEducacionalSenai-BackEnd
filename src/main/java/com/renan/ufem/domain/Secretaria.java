package com.renan.ufem.domain;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "secretaria")
@Data
public class Secretaria implements UsuarioAutenticavel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id_secretaria;

	private String nome;
	private String logradouro;
	private String bairro;
	private int numero;
	private String cidade;
	private String UF;
	private String email;
	private String senha;
	private String telefone;

	@Override
	public String getLogin() {
		return this.email; // Secretaria se autentica com email
	}

	@Override
	public String getUsername() {
		return getLogin();
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_SECRETARIA"));
	}

	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
}
