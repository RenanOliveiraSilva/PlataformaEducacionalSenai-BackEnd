package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semestre_disciplina")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    private String professor;
    private String diaSemana;
}