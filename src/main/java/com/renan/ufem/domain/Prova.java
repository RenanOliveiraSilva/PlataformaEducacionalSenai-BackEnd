package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "prova")
@Data
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prova")
    private Integer idProva;

    private String nome;
    private LocalDate data;
    private Float peso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_turma")
    private Turma turma;
}
