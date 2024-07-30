package com.forohub.principal.service;

import com.forohub.principal.dto.DtoUsuario;
import com.forohub.principal.models.AuthenticationRequest;
import com.forohub.principal.models.AuthenticationResponse;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final ServicioGenerales servicioGenerales;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;
    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, ServicioGenerales servicioGenerales, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.usuarioRepository = usuarioRepository;
        this.servicioGenerales = servicioGenerales;
        this.passwordEncoder=passwordEncoder;
        this.authenticationService=authenticationService;
    }

    @Transactional
    public List<DtoUsuario> traerUsuarios(){
        return transformarDTO(usuarioRepository.findAll());
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
    public String crearUsuario(DtoUsuario usuario){
        Usuario usuario1 = new Usuario(usuario);
        usuario1.setPassword(passwordEncoder.encode(usuario.password()));
        crearUsuario(usuario1);
        return "Creado con exito";
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
        usuario.orElseThrow(()->new RuntimeException("Ocurrio un error No se encontraron usuarios"));
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            return usuario1;
        }
    }
    public DtoUsuario traerPorID(Long id){
        return traerUsuariosPorID(id).toDto();
    }
    public DtoUsuario actualizarPorID(Long id, DtoUsuario dtoUsuario){
        Usuario usuario = traerUsuariosPorID(id);
        boolean nombreNuevo = servicioGenerales.controlarEstado(dtoUsuario.nombre(), usuario.getNombre());
        boolean correoNuevo = servicioGenerales.controlarEstado(dtoUsuario.correoElectronico(), usuario.getCorreoElectronico());
        boolean contrasenaNuevo = servicioGenerales.controlarEstado(dtoUsuario.password(), usuario.getPassword());
        if(nombreNuevo){
            usuario.setNombre(dtoUsuario.nombre());
        }
        if(correoNuevo){
            usuario.setCorreoElectronico(dtoUsuario.correoElectronico());
        }
        if(contrasenaNuevo){
            usuario.setPassword(dtoUsuario.password());
        }
        if(contrasenaNuevo|nombreNuevo|correoNuevo){
            usuarioRepository.save(usuario);
        }
        return transformarDTO(usuario);
    }






    public List<DtoUsuario> transformarDTO(List<Usuario> usuarios){
        return usuarios.stream().map(Usuario::toDto).collect(Collectors.toList());
    }
    public DtoUsuario transformarDTO(Usuario usuario){
        return  usuario.toDto();
    }


    public AuthenticationResponse authenticarUsuario(AuthenticationRequest authenticationRequest) {
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println("Soy login");
        System.out.println(authenticationService.login(authenticationRequest));
        return authenticationService.login(authenticationRequest);




    }
}
