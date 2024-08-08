package com.forohub.principal.models;

import com.forohub.principal.dto.request.DtoUsuario;
import com.forohub.principal.dto.response.DtoUsuarioResponse;
import com.forohub.principal.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String username;
    @Email
    @Column(unique = true)
    private String correoElectronico;

    private String password;
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas = new ArrayList();
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Topico> topicos = new ArrayList();
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Perfil> perfiles;
    @Enumerated(EnumType.STRING)
    private Role role;
    public void agregarPerfil(Perfil perfil, Usuario usuario) {
        List<Perfil> perfiles = usuario.traerPerfilesUsuario(usuario);
        perfiles.add(perfil);
        perfil.setUsuario(this);
    }

    public Usuario(String nombre, String username, String correoElectronico, String password,Role role) {
        this.nombre = nombre;
        this.username = username;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.role=role;
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
        this.username=dtoUsuario.username();
        this.role=dtoUsuario.role();
        this.correoElectronico = dtoUsuario.correoElectronico();
        this.password = dtoUsuario.password();
    }
    public Usuario(DtoUsuarioResponse dtoUsuarioResponse){
        this.nombre = dtoUsuarioResponse.nombre();
        this.username=dtoUsuarioResponse.username();
        this.role=dtoUsuarioResponse.role();
        this.correoElectronico = dtoUsuarioResponse.correoElectronico();
    }
    public DtoUsuarioResponse toDto() {
        return new DtoUsuarioResponse(this.nombre, this.correoElectronico, (List)this.respuestas.stream().map(Respuesta::toDto).collect(Collectors.toList()), (List)this.topicos.stream().map(Topico::toDto).collect(Collectors.toList()), (List)this.perfiles.stream().map(Perfil::toDto).collect(Collectors.toList()),this.role,this.username);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
















}
