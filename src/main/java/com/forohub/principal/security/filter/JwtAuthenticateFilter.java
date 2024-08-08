package com.forohub.principal.security.filter;

import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.split(" ")[1];
        String username = jwtService.extractUsername(jwt);
        Usuario usuario1 = usuarioRepository.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                username,null,usuario1.getAuthorities()
        );
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);
        System.out.println(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
        System.out.println("paso el filtro");
    }
}
