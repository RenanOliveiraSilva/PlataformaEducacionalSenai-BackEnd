package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "notaprova")
@Data
public class NotaProva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_prova")
    private Integer idNotaProva;

    private Float nota;

    @Column(name = "status_prova")
    private String statusProva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_prova")
    private Prova prova;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
}
