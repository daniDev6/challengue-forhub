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
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    ICursoRepository cursoRepository;
    @Autowired
    ITopicoRepository topicoRepository;


    public List<DtoTopico> traerTopicos(){
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream().map(e->e.toDto()).collect(Collectors.toList());
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


    public Topico traerTopicoPorID(Long topico_id) {
        Topico topico = topicoRepository.getReferenceById(topico_id);
        return topico;
    }
}
