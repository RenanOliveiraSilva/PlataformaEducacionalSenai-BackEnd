package com.renan.ufem.infra.security.secretaria;

import com.renan.ufem.domain.secretaria.Secretaria;
import com.renan.ufem.repositories.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomSecretariaDetailsService implements UserDetailsService {

    private final SecretariaRepository repository;

    @Autowired
    public CustomSecretariaDetailsService(SecretariaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Secretaria secretaria = this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Secretaria not found"));

        return new org.springframework.security.core.userdetails.User(
                secretaria.getEmail(),
                secretaria.getSenha(),
                new ArrayList<>()
        );
    }
}
