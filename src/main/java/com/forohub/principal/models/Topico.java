package com.forohub.principal.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private boolean status;
    private Usuario usuario;
    private Curso curso;
    private List<Respuesta> respuesta;
}
