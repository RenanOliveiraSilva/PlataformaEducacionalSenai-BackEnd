package com.renan.ufem.infra.security.aluno;

import com.renan.ufem.domain.aluno.Aluno;
import com.renan.ufem.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAlunoDetailsService implements UserDetailsService {

    private final AlunoRepository repository;

    @Autowired
    public CustomAlunoDetailsService(AlunoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Aluno aluno = this.repository.findByCPF(username)
                .orElseThrow(() -> new UsernameNotFoundException("Aluno not found"));

        return new org.springframework.security.core.userdetails.User(
                aluno.getEmail(),
                aluno.getSenha(),
                new ArrayList<>()
        );
    }
}
