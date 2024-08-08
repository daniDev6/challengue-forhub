package com.forohub.principal;

import com.forohub.principal.enums.Role;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class  CrearUsuario {
    private IUsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    @Autowired
    public CrearUsuario(IUsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    public void crearAdmin(){

        if(usuarioRepository.findByUsername("admin").isPresent()){
            System.out.println("Administrador ya creado");
            return;
        }
        Role adminRole = Role.ADMINISTRATOR;
        Usuario admin = new Usuario("admin","admin","admin@admin","admin123",adminRole);
        try{
            usuarioService.crearUsuario(admin);
        }catch (Exception e){
            new RuntimeException("Error al crear el usuario " + e);
        }
    }
    public void crearUser(){

        if(usuarioRepository.findByUsername("user").isPresent()){
            System.out.println("Usuario ya creado");
            return;
        }
        Role userRole = Role.CUSTOMER;
        Usuario user = new Usuario("user","user","user@user","user123",userRole);
        try{
            usuarioService.crearUsuario(user);
        }catch (Exception e){
            new RuntimeException("Error al crear el usuario " + e);
        }
    }



















}
