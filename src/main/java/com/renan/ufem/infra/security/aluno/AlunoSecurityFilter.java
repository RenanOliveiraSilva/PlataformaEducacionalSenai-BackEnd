package com.renan.ufem.infra.security.aluno;

import com.renan.ufem.domain.aluno.Aluno;
import com.renan.ufem.repositories.AlunoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AlunoSecurityFilter extends OncePerRequestFilter {
    @Autowired
    AlunoTokenService tokenService;
    @Autowired
    AlunoRepository alunoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if(login != null){
            Aluno aluno = alunoRepository.findByCPF(login).orElseThrow(() -> new RuntimeException("Aluno Not Found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ALUNO"));
            var authentication = new UsernamePasswordAuthenticationToken(aluno, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
