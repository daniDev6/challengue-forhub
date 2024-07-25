package com.forohub.principal.models;

import com.forohub.principal.dto.DtoCurso;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nombre;
    private String categoria;
    @OneToMany(
            mappedBy = "curso",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<Topico> topicos = new ArrayList();

    public void agregarTopico(Topico topico) {
        this.topicos.add(topico);
        topico.setCurso(this);
    }

    public Curso(DtoCurso dtoCurso) {
        this.nombre = dtoCurso.nombre();
        this.categoria = dtoCurso.categoria();
    }

    public DtoCurso toDto() {
        return new DtoCurso(this.nombre, this.categoria, this.topicos.stream().map(Topico::toDto).collect(Collectors.toList()));
    }

}
