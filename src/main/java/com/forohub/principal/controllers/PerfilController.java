package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoPerfil;
import com.forohub.principal.models.Perfil;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.service.PerfilService;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("perfil/")
public class PerfilController {
    @Autowired
    PerfilService perfilService;
    @Autowired
    UsuarioService usuarioService;

    public PerfilController() {
    }

    @GetMapping("traer")
    public List<DtoPerfil> traerPerfiles() {
        return this.perfilService.traerPerfiles();
    }

    @PostMapping("crear")
    @Transactional
    public String crearPerfil(@RequestBody DtoPerfil perfil) {
        Usuario usuario = this.usuarioService.traerUsuariosPorID(perfil.usuario_id());
        Perfil perfil2 = new Perfil(perfil, usuario);
        usuario.agregarPerfil(perfil2, usuario);
        System.out.println(usuario);
        System.out.println(usuario.getPerfiles());
        this.usuarioService.crearUsuario(usuario);
        perfil2.setUsuario(usuario);
        return this.perfilService.crearPerfil(perfil2);
    }

}
