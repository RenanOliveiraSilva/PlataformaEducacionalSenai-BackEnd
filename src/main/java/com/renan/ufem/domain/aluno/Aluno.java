package com.renan.ufem.domain.aluno;

import com.renan.ufem.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_aluno;
    private String matricula;

}
