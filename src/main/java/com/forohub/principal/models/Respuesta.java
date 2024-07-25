package com.forohub.principal.models;

import com.forohub.principal.dto.DtoRespuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private String solucion;

    public Respuesta(DtoRespuesta dtoRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = dtoRespuesta.mensaje();
        this.solucion = dtoRespuesta.solucion();
        this.usuario = usuario;
        this.topico = topico;
    }



    public DtoRespuesta toDto() {
        return new DtoRespuesta(this.mensaje, this.fechaCreacion, this.usuario.getId(), this.topico.getId(), this.solucion);
    }
}
