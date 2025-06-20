package com.renan.ufem.domain;

import com.renan.ufem.enums.AtividadeStatus;
import com.renan.ufem.enums.StatusSemestre;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "atividadealuno")
@Data
public class AtividadeAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_atividade_aluno")
    private String idAtividadeAluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_atividade")
    private Atividade atividade;

    @Column(name = "nota", nullable = true)
    private Float nota;

    @Enumerated(EnumType.STRING)
    private AtividadeStatus status;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;
}
