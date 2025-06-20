package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prova")
@Data
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_prova")
    private String idProva;

    private String nome;
    private LocalDate data;
    private Float peso;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL)
    private List<NotaProva> notas;
}
