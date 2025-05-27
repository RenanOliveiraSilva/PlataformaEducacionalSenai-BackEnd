package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "turma")
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_turma")
    private String idTurma;

    private String nome;
    private String ano;

    @Column(name = "id_curso")
    private String idCurso;

    @Column(name = "id_secretaria")
    private String idSecretaria;

    // Mapeamento do relacionamento com Aluno
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;
}
