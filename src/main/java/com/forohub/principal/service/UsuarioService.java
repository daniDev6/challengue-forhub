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
    public List<DtoUsuario> traerUsuarios() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        List<DtoUsuario> usuariosDto = (List)usuarios.stream().map((e) -> {
            return e.toDto();
        }).collect(Collectors.toList());
        return usuariosDto;
    }

    @Transactional
    public String crearUsuario(Usuario usuario) {
        try {
            this.usuarioRepository.save(usuario);
            return "se creo correctamente";
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public Usuario traerUsuariosPorID(Long id) {
        Usuario usuario1 = new Usuario();

        Optional usuario;
        try {
            usuario = usuarioRepository.findById(id);
        } catch (Exception var5) {
            throw new RuntimeException();
        }

        return usuario.isPresent() ? usuario.get() : usuario1;
    }
}
