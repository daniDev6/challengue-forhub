package com.forohub.principal.models;

import com.forohub.principal.dto.DtoPerfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perfiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Perfil(DtoPerfil dtoPerfil,Usuario usuario) {
        this.nombre=dtoPerfil.nombre();
        this.usuario=usuario;
    }




    public DtoPerfil toDto() {
        return new DtoPerfil(this.nombre,this.usuario.getId());
    }
}