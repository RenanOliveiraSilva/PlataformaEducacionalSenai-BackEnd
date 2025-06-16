package com.renan.ufem.domain;

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

    private Float nota;

    private String status;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;
}
