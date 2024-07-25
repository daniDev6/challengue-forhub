package com.forohub.principal.models;

import com.forohub.principal.dto.DtoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas = new ArrayList();
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Topico> topicos = new ArrayList();
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Perfil> perfiles;

    public void agregarPerfil(Perfil perfil, Usuario usuario) {
        List<Perfil> perfiles = usuario.traerPerfilesUsuario(usuario);
        perfiles.add(perfil);
        perfil.setUsuario(this);
    }

    public List<Perfil> traerPerfilesUsuario(Usuario usuario) {
        return usuario.perfiles == null ? new ArrayList() : usuario.perfiles;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
        respuesta.setUsuario(this);
    }

    public void agregarTopicos(Topico topico) {
        this.topicos.add(topico);
        topico.setUsuario(this);
    }

    public Usuario(DtoUsuario dtoUsuario) {
        this.nombre = dtoUsuario.nombre();
        this.correoElectronico = dtoUsuario.correoElectronico();
        this.contrasena = dtoUsuario.contrasena();
    }

    public DtoUsuario toDto() {
        return new DtoUsuario(this.nombre, this.correoElectronico, this.contrasena, (List)this.respuestas.stream().map(Respuesta::toDto).collect(Collectors.toList()), (List)this.topicos.stream().map(Topico::toDto).collect(Collectors.toList()), (List)this.perfiles.stream().map(Perfil::toDto).collect(Collectors.toList()));
    }
}
