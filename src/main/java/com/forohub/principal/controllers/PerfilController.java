package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoCurso;
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
    private final PerfilService perfilService;
    private final UsuarioService usuarioService;

    @Autowired
    public PerfilController(PerfilService perfilService, UsuarioService usuarioService) {
        this.perfilService = perfilService;
        this.usuarioService = usuarioService;
    }



    @GetMapping("traer")
    public List<DtoPerfil> traerPerfiles() {
        return perfilService.traerPerfiles();
    }

    @PostMapping("crear")
    @Transactional
    public String crearPerfil(@RequestBody DtoPerfil perfil) {
        Usuario usuario = usuarioService.traerUsuariosPorID(perfil.usuario_id());
        Perfil perfil2 = new Perfil(perfil, usuario);
        usuario.agregarPerfil(perfil2, usuario);
        perfil2.setUsuario(usuario);
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("traer/{id}")
    public DtoPerfil traerPerfilID(@PathVariable Long id){
        return perfilService.traerPorID(id);
    }
    @PutMapping("actualizar/{id}")
    public String actualizarPorID(@RequestBody DtoPerfil dtoPerfil, @PathVariable Long id){
        return perfilService.actualizarPorID(id,dtoPerfil);
    }










}
