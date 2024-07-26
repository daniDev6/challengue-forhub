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
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @GetMapping("traer")
    public List<DtoUsuario> traerUsuarios() {
        return this.usuarioService.traerUsuarios();
    }

    @GetMapping("traer/{id}")
    public DtoUsuario traerUsuariosPorID(@PathVariable Long id) {
        return usuarioService.traerPorID(id);
    }

    @PostMapping("crear")
    public String crearUsuario(@RequestBody DtoUsuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @PutMapping("actualizar/{id}")
    public DtoUsuario actualizarPorID(@PathVariable Long id,@RequestBody DtoUsuario dtoUsuario){
        return usuarioService.actualizarPorID(id, dtoUsuario);
    }











}
