package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "nota")
@Data
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Integer idNota;

    private Float valor;
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
}