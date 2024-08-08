package com.forohub.principal.controllers;

import com.forohub.principal.dto.request.DtoUsuario;
import com.forohub.principal.dto.response.DtoUsuarioResponse;
import com.forohub.principal.models.AuthenticationRequest;
import com.forohub.principal.models.AuthenticationResponse;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.security.service.AuthenticationService;
import com.forohub.principal.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private AuthenticationService authenticationService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService,AuthenticationService authenticationService) {
        this.authenticationService=authenticationService;
        this.usuarioService = usuarioService;
    }



    @GetMapping("traer")
    public List<DtoUsuarioResponse> traerUsuarios() {
        System.out.println("Entrando a traer get");


        return this.usuarioService.traerUsuarios();
    }

    @GetMapping("traer/{id}")
    public DtoUsuarioResponse traerUsuariosPorID(@PathVariable Long id) {
        return usuarioService.traerPorID(id);
    }

    @PostMapping("crear")
    public String crearUsuario(@RequestBody DtoUsuario usuario) {
        Usuario usuario1=new Usuario(usuario);
        return usuarioService.crearUsuario(usuario1);
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse jwtDto= authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @PutMapping("actualizar/{id}")
    public DtoUsuarioResponse actualizarPorID(@PathVariable Long id,@RequestBody DtoUsuario dtoUsuario){
        return usuarioService.actualizarPorID(id, dtoUsuario);
    }

    @DeleteMapping("eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        return usuarioService.eliminarCurso(id);
    }









}
