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

    public TopicoService() {
    }

    public List<DtoTopico> traerTopicos() {
        List<Topico> topicos = this.topicoRepository.findAll();
        return topicos.stream().map(Topico::toDto).collect(Collectors.toList());
    }

    @Transactional
    public String crearTopico(Topico topico) {
        try {
            this.topicoRepository.save(topico);
            return "Creado con exito";
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public Topico traerPorID(Long id) {
        Optional<Topico> topico;
        try{
            topico = topicoRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return topico.isPresent()?topico.get():new Topico();
    }
}
