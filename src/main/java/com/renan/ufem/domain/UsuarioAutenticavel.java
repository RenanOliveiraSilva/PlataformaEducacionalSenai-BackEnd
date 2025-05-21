package com.renan.ufem.repositories;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioAutenticavel extends UserDetails {
    String getLogin(); // pode ser CPF, email, etc.
}
