package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semestre_disciplina")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDisciplina {

    @EmbeddedId
    private SemestreDisciplinaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSemestre")
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDisciplina")
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProfessor")
    @JoinColumn(name = "id_professor")
    private Professor professor;

    private String diaSemana;
}
