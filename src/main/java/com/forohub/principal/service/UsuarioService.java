package com.forohub.principal.service;

import com.forohub.principal.dto.DtoUsuario;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsuarioService {
    IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional
    public List<DtoUsuario> traerUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<DtoUsuario> usuariosDto = usuarios.stream()
                .map(e->e.toDto())
                .collect(Collectors.toList());
        return usuariosDto;
    }
    @Transactional
    public String crearUsuario(Usuario usuario){
        try{
            usuarioRepository.save(usuario);
        }catch (Exception e){
            throw  new RuntimeException();
        }
        return "se creo correctamente";
    }

    @Transactional
    public Usuario traerUsuariosPorID(Long id) {
        Optional<Usuario> usuario;
        Usuario usuario1 = new Usuario();
        try{
            usuario=usuarioRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            return usuario1;
        }


    }
}
