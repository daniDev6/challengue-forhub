package com.forohub.principal.service;

import com.forohub.principal.dto.DtoRespuesta;
import com.forohub.principal.models.Respuesta;
import com.forohub.principal.repository.IRespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RespuestaService {
    @Autowired
    IRespuestaRepository respuestaRepository;

    public RespuestaService() {
    }

    public List<DtoRespuesta> traerRespuestas() {
        List<Respuesta> listaRespuestas = this.respuestaRepository.findAll();
        return listaRespuestas.stream().map(Respuesta::toDto).collect(Collectors.toList());
    }

    public DtoRespuesta traerRespuestasPorID(Long id) {
        Optional<Respuesta> respuesta = this.respuestaRepository.findById(id);
        respuesta.orElseThrow(() -> {
            return new RuntimeException("no se encontraron resultados");
        });
        return (respuesta.get()).toDto();
    }

    public String crearRespuesta(Respuesta respuesta) {
        try {
            this.respuestaRepository.save(respuesta);
            return "Se creo con exito la respuesta";
        } catch (Exception var3) {
            throw new RuntimeException("error al crear una respuesta");
        }
    }
}
