package com.forohub.principal.models;

import java.util.List;

public class Usuario {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private List<Perfil> perfil;
}
