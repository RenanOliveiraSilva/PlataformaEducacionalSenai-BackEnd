package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "grade")
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_grade")
    private String idGrade;

    @Column(name = "id_turma")
    private String idTurma;

    @Column(name = "id_curso")
    private String idCurso;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Semestre> semestres;
}


