package com.renan.ufem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.renan.ufem.enums.StatusSemestre;
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

    @Column(name = "numero_semestre")
    private Integer numero;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_grade")
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private StatusSemestre status;

    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SemestreDisciplina> semestreDisciplinas;
}


