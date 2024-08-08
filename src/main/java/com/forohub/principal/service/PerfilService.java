package com.forohub.principal.service;

import com.forohub.principal.dto.request.DtoPerfil;
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
    private final IPerfilRepository perfilRepository;
    private final ServicioGenerales servicioGenerales;

    public PerfilService(IPerfilRepository perfilRepository, ServicioGenerales servicioGenerales) {
        this.perfilRepository = perfilRepository;
        this.servicioGenerales = servicioGenerales;
    }

    @Autowired



    @Transactional
    public List<DtoPerfil> traerPerfiles() {
        List<Perfil> perfiles = perfilRepository.findAll();
        return transformarDTO(perfiles);
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
    public DtoPerfil traerPorID(Long id) {
        Optional<Perfil> perfil;
        try{
            perfil = perfilRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return perfil.isPresent()?transformarDTO(perfil.get()):transformarDTO(new Perfil());
    }

    public DtoPerfil transformarDTO(Perfil perfil){
        return perfil.toDto();
    }
    public List<DtoPerfil> transformarDTO(List<Perfil> perfiles){
        return perfiles.stream().map(Perfil::toDto).collect(Collectors.toList());
    }


    public String actualizarPorID(Long id,DtoPerfil dtoPerfil) {
        //exite?
        Optional<Perfil> perfil = perfilRepository.findById(id);
        if(perfil.isPresent()){
            //validamos q los datos no sean lo mismos de los que ya tenia
            boolean nombreNuevo = servicioGenerales.controlarEstado(dtoPerfil.nombre(),perfil.get().getNombre());

            if(nombreNuevo){
                perfil.get().setNombre(dtoPerfil.nombre());
                perfilRepository.save(perfil.get());
                return "Se actualizo con exito";
            }else {
                return "No se encontraron cambios";
            }
        }else {
            return "No existe el perfil que se desea actuializar";
        }


    }


    public String eliminarPerfil(Long id) {
        try{
            perfilRepository.deleteById(id);
            return "Se borro correctamente";
        }catch (Exception e){
            new RuntimeException(e);
            return "Error al intentar borrar";
        }
    }
}
