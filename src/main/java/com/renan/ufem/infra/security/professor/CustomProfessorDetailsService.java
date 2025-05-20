package com.renan.ufem.infra.security.professor;

import com.renan.ufem.domain.professor.Professor;
import com.renan.ufem.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomProfessorDetailsService implements UserDetailsService {

    private final ProfessorRepository repository;

    @Autowired
    public CustomProfessorDetailsService(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Professor professor = this.repository.findByCPF(username)
                .orElseThrow(() -> new UsernameNotFoundException("Professor not found"));

        return new org.springframework.security.core.userdetails.User(
                professor.getEmail(),
                professor.getSenha(),
                new ArrayList<>()
        );
    }
}
