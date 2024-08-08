package com.forohub.principal.security.service;

import com.forohub.principal.models.AuthenticationRequest;
import com.forohub.principal.models.AuthenticationResponse;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class AuthenticationService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),authenticationRequest.getPassword()
        );
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        System.out.println("Entrando al authenticate");
        authenticationManager.authenticate(authenticationToken);
        System.out.println("Saliendo del authenticate");
        System.out.println("Saliendo del authenticate");
        System.out.println("Saliendo del authenticate");
        System.out.println("Saliendo del authenticate");
        System.out.println("Saliendo del authenticate");
        System.out.println("Saliendo del authenticate");
        Usuario usuario = usuarioRepository.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(usuario,generateExtraClaims(usuario));
        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String,Object> extraClaim=new HashMap<>();
        extraClaim.put("name",usuario.getNombre());
        extraClaim.put("role",usuario.getRole());
        extraClaim.put("permiossion",usuario.getAuthorities());
        return extraClaim;
    }


}
