package com.forohub.principal.service;

import com.forohub.principal.dto.DtoTopico;
import com.forohub.principal.models.Perfil;
import com.forohub.principal.models.Topico;
import com.forohub.principal.repository.ICursoRepository;
import com.forohub.principal.repository.ITopicoRepository;
import com.forohub.principal.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TopicoService {

    private final IUsuarioRepository usuarioRepository;

    private final ICursoRepository cursoRepository;

    private final ITopicoRepository topicoRepository;
    private final ServicioGenerales servicioGenerales;

    public TopicoService(ServicioGenerales servicioGenerales, IUsuarioRepository usuarioRepository, ICursoRepository cursoRepository, ITopicoRepository topicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.topicoRepository = topicoRepository;
        this.servicioGenerales = servicioGenerales;
    }

    public List<DtoTopico> traerTopicos(){
        return transformarDTO(topicoRepository.findAll());
    }
    @Transactional
    public String crearTopico(Topico topico){
        try{
            topicoRepository.save(topico);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return "Creado con exito";
    }



    public Topico traerPorID(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        topico.orElseThrow(()->new RuntimeException("No se encontro"));
        return topico.get();
    }
    public DtoTopico traerTopicoPorID(Long id){
        return transformarDTO(traerPorID(id));
    }


    public DtoTopico transformarDTO(Topico topico){
        return topico.toDto();
    }
    public List<DtoTopico> transformarDTO(List<Topico> topicos){
        return topicos.stream().map(Topico::toDto).collect(Collectors.toList());
    }


    public String actualizarTopicoPorID(Long id,DtoTopico dtoTopico) {
       Topico topico = traerPorID(id);
       //analizamos si los datos nuevos difieren
        boolean tituloNuevo = servicioGenerales.controlarEstado(dtoTopico.titulo(),topico.getTitulo());
        boolean statusNuevo = servicioGenerales.controlarEstado(dtoTopico.status(),topico.isStatus());
        if(tituloNuevo){
            topico.setTitulo(dtoTopico.titulo());
        }
        if(statusNuevo){
            topico.setStatus(dtoTopico.status());
        }
        if(tituloNuevo|statusNuevo){
            topicoRepository.save(topico);
            return "Se efectuaron los cambios";
        }else {
            return "No se notaron cambios";
        }

    }
}
