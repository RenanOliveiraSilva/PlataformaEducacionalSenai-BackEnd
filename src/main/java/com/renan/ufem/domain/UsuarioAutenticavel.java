package com.renan.ufem.domain;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioAutenticavel extends UserDetails {
    String getLogin(); // pode ser CPF, email, etc.
}
