package com.renan.ufem.dto.prova;
import java.time.LocalDate;

public record ProvaCreateDTO(String nome, LocalDate data, Float peso) {}
