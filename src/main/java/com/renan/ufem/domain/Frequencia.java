package com.renan.ufem.domain;

import com.renan.ufem.enums.StatusFrequencia;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "frequencia")
@Data
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_frequencia")
    private String idFrequencia;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusFrequencia status;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;
}

