package com.renan.ufem.domain;

import com.renan.ufem.enums.AtividadeStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notasprova")
@Data
public class NotaProva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_nota_prova")
    private String idNotaProva;

    private Float nota;

    @Enumerated(EnumType.STRING)
    private AtividadeStatus statusProva;

    @ManyToOne
    @JoinColumn(name = "id_prova")
    private Prova prova;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
}