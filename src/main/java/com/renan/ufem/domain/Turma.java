package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;

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
    private String id_curso;
    private String id_secretaria;
}
