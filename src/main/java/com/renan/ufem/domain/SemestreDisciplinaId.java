package com.renan.ufem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDisciplinaId implements Serializable {

    @Column(name = "id_semestre")
    private String idSemestre;

    @Column(name = "id_disciplina")
    private String idDisciplina;

    @Column(name = "id_professor")
    private String idProfessor;
}


