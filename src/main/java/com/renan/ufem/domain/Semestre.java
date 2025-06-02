package com.renan.ufem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "semestre")
@Data
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_semestre")
    private String idSemestre;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "id_grade")
    private Grade grade;

    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private List<SemestreDisciplina> disciplinas;
}


