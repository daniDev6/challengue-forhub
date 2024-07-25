package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoRespuesta;
import com.forohub.principal.models.Respuesta;
import com.forohub.principal.models.Topico;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.service.RespuestaService;
import com.forohub.principal.service.TopicoService;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("respuesta/")
public class RespuestaController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TopicoService topicoService;
    @Autowired
    RespuestaService respuestaService;

    public RespuestaController() {
    }

    @GetMapping("traer")
    public List<DtoRespuesta> traerRespuestas() {
        return respuestaService.traerRespuestas();
    }

    @PostMapping("crear")
    public String crearRespuesta(@RequestBody DtoRespuesta dtoRespuesta) {
        Usuario usuario = this.usuarioService.traerUsuariosPorID(dtoRespuesta.usuario_id());
        Topico topico = this.topicoService.traerTopicoPorID(dtoRespuesta.topico_id());
        Respuesta respuesta = new Respuesta(dtoRespuesta, usuario, topico);
        return this.respuestaService.crearRespuesta(respuesta);
    }

}
