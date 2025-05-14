package com.renan.ufem.infra.security;

import com.renan.ufem.domain.secretaria.Secretaria;
import com.renan.ufem.repositories.SecretariaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomSecretariaDetailsService implements UserDetailsService {

    private SecretariaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Secretaria secretaria = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Secretaria not found"));
        return new org.springframework.security.core.userdetails.User(secretaria.getEmail(), secretaria.getSenha(), new ArrayList<>());
    }
}
