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
    private final IRespuestaRepository respuestaRepository;
    private final ServicioGenerales servicioGenerales;

    @Autowired
    public RespuestaService(IRespuestaRepository respuestaRepository, ServicioGenerales servicioGenerales) {
        this.respuestaRepository = respuestaRepository;
        this.servicioGenerales = servicioGenerales;
    }





    public List<DtoRespuesta> traerRespuestas(){
        return transformarDTO(respuestaRepository.findAll());
    }
    public DtoRespuesta traerRespuestasPorID(Long id){
        return transformarDTO(traerPorID(id));
    }
    public Respuesta traerPorID(Long id){
        Optional<Respuesta> respuesta=respuestaRepository.findById(id);
        respuesta.orElseThrow(()->new RuntimeException("no se encontraron resultados"));
        return respuesta.get();
    }
    public String crearRespuesta(Respuesta respuesta){
        try{
            respuestaRepository.save(respuesta);
        }catch (Exception e){
            throw new RuntimeException("error al crear una respuesta");
        }
        return "Se creo con exito la respuesta";
    }

    public String actualizarPorID(Long id, DtoRespuesta dtoRespuesta) {
        Respuesta respuesta = traerPorID(id);
        //evaluamos si los datos son diferentes
        boolean mensajeNuevo = servicioGenerales.controlarEstado(dtoRespuesta.mensaje(),respuesta.getMensaje());
        boolean solucionNuevo = servicioGenerales.controlarEstado(dtoRespuesta.solucion(),respuesta.getSolucion());
        if(mensajeNuevo){
            respuesta.setMensaje(dtoRespuesta.mensaje());
        }
        if(solucionNuevo){
            respuesta.setSolucion(dtoRespuesta.solucion());
        }
        if(mensajeNuevo|solucionNuevo){
            respuestaRepository.save(respuesta);
            return "Se efectuaron los cambios";
        }else {
            return "Sin cambios";
        }
    }






    public DtoRespuesta transformarDTO(Respuesta respuesta){
        return respuesta.toDto();
    }
    public List<DtoRespuesta> transformarDTO(List<Respuesta> respuestas){
        return respuestas.stream().map(Respuesta::toDto).collect(Collectors.toList());
    }



}
