package com.forohub.principal.models;

import java.time.LocalDate;

public class Respuesta {
    private Long id;
    private String mensaje;
    private LocalDate fechaCreacion;
    private Usuario usuario;
    private Topico topico;
    private String solucion;
}
