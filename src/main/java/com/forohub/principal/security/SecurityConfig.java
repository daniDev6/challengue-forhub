package com.forohub.principal.security;

import com.forohub.principal.enums.Permission;
import com.forohub.principal.enums.Role;
import com.forohub.principal.security.filter.JwtAuthenticateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticateFilter jwtAuthenticationFilter;
    @Autowired
    AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig->csrfConfig.disable())//cross vulnerabilidades web, agarrar la sesion del usuario
                .sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//indicamos como va a ser las sesiones o tipos de configuraciones
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig->{
                    authConfig.requestMatchers(HttpMethod.POST,"/usuarios/crear").hasAuthority(Permission.GUARDAR.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/usuarios/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/usuarios/traer").hasAuthority(Permission.LEER_TODOS.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/topico/traer").hasRole(Role.ADMINISTRADOR.name());
                    authConfig.anyRequest().authenticated();
                });


        return http.build();

    }

}
