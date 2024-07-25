package com.forohub.principal.models;

import com.forohub.principal.dto.DtoTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas = new ArrayList();

    public Topico(DtoTopico dtoTopico, Usuario usuario, Curso curso) {
        this.titulo = dtoTopico.titulo();
        this.status = dtoTopico.status();
        this.usuario = usuario;
        this.curso = curso;
    }


    public void setRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
        respuesta.setTopico(this);
    }

    public DtoTopico toDto() {
        return new DtoTopico(this.titulo, this.fechaCreacion, this.status, this.usuario.getId(), this.curso.getId(), this.respuestas.stream().map(Respuesta::toDto).collect(Collectors.toList()));
    }
}
