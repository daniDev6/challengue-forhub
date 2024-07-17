package com.forohub.principal.controllers;

import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {
    @Autowired
    IUsuarioRepository usuarioRepository;
    @GetMapping("traer")
    public List<Usuario> traerUsuarios(){
        return usuarioRepository.findAll();
    }


}
