package com.renan.ufem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "grade")
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_grade")
    private String idGrade;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
    private Turma turma;

    @Column(name = "id_curso")
    private String idCurso;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Semestre> semestres;
}


