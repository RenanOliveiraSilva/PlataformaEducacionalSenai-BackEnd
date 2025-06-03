package com.renan.ufem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renan.ufem.enums.DiaSemana;
import com.renan.ufem.enums.StatusSemestre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semestredisciplina")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDisciplina {

    @EmbeddedId
    private SemestreDisciplinaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId("idSemestre")
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId("idDisciplina")
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId("idProfessor")
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "dia_semana")
    private DiaSemana diaSemana;

    @Enumerated(EnumType.STRING)
    private StatusSemestre status;
}
