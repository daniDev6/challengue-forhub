package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoTopico;
import com.forohub.principal.models.Curso;
import com.forohub.principal.models.Topico;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.service.CursoService;
import com.forohub.principal.service.TopicoService;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topico/")
public class TopicoController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CursoService cursoService;
    @Autowired
    TopicoService topicoService;


    @PostMapping("crear")
    public String crearTopico(@RequestBody DtoTopico dtoTopico) {
        Usuario user = this.usuarioService.traerUsuariosPorID(dtoTopico.usuario_id());
        Curso curso = this.cursoService.traerPorCursoPorID(dtoTopico.curso_id());
        Topico topico = new Topico(dtoTopico, user, curso);
        return this.topicoService.crearTopico(topico);
    }










}
