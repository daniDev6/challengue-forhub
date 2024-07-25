package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoUsuario;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    IUsuarioRepository usuarioRepository;

    public UsuarioController() {
    }

    @GetMapping("traer")
    public List<DtoUsuario> traerUsuarios() {
        return this.usuarioService.traerUsuarios();
    }

    @GetMapping("traer/{id}")
    public DtoUsuario traerUsuariosPorID(@PathVariable Long id) {
        return usuarioService.traerUsuariosPorID(id).toDto();
    }

    @PostMapping("crear")
    public String crearUsuario(@RequestBody DtoUsuario usuario) {
        Usuario user = new Usuario(usuario);
        return usuarioService.crearUsuario(user);
    }
}
