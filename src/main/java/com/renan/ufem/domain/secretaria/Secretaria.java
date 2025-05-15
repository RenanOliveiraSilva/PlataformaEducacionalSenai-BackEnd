package com.renan.ufem.domain.secretaria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "secretaria")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id_secretaria;
	private String nome;
	private String email;
	private String telefone;
	private String senha;
}
