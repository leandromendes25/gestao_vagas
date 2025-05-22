package br.testando.gestao_vagas.security;

import br.testando.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Para remover qualquer sujeira
        SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");
        if (header != null) {
           var subjectToken = jwtProvider.validateToken(header);
            if (subjectToken.isEmpty()) {
                //retorna erro
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; //para sair do método
            }
            request.setAttribute("company_id", subjectToken);
            //Insere o usuario no spring security
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.EMPTY_LIST);
            //Injeta o auth dentro do spring security
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
