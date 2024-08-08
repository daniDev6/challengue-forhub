package com.forohub.principal.controllers;

import com.forohub.principal.dto.request.DtoTopico;
import com.forohub.principal.dto.response.DtoTopicoResponse;
import com.forohub.principal.models.Curso;
import com.forohub.principal.models.Topico;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.service.CursoService;
import com.forohub.principal.service.TopicoService;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("traer/{id}")
    public DtoTopicoResponse traerTopicoPorID(@PathVariable Long id){
        return topicoService.traerTopicoPorID(id);
    }

    @GetMapping("traer")
    public List<DtoTopicoResponse> traerTopicos(){
        return topicoService.traerTopicos();
    }
    @PutMapping("actualizar/{id}")
    public String actualizarPorID(@RequestBody DtoTopico dtoTopico,@PathVariable Long id){
        return topicoService.actualizarTopicoPorID(id,dtoTopico);
    }
    @DeleteMapping("eliminar/{id}")
    public String borrarTopico(@PathVariable Long id){
        return topicoService.eliminarPerfil(id);
    }







}
