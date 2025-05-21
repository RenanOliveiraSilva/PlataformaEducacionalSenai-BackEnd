package com.renan.ufem.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String UF;
    private String telefone;
    private String Sexo;
    private Date data_nasc;
}
