package com.forohub.principal.service;

import com.forohub.principal.dto.DtoPerfil;
import com.forohub.principal.models.Curso;
import com.forohub.principal.models.Perfil;
import com.forohub.principal.repository.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PerfilService {
    @Autowired
    IPerfilRepository perfilRepository;

    @Transactional
    public List<DtoPerfil> traerPerfiles() {
        List<Perfil> perfiles = perfilRepository.findAll();
        return perfiles.stream().map(Perfil::toDto).collect(Collectors.toList());
    }

    @Transactional
    public String crearPerfil(Perfil perfil) {
        try {
            perfilRepository.save(perfil);
            return "Se creo correctamente";
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }
    @Transactional
    public Perfil traerPorID(Long id) {
        Optional<Perfil> perfil;
        try{
            perfil = perfilRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return perfil.isPresent()?perfil.get():new Perfil();
    }

}
