package com.forohub.principal.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {

    USUARIO(Arrays.asList(Permission.LEER_TODOS)),
    ADMINISTRADOR(Arrays.asList(Permission.ACTUALIZAR,Permission.GUARDAR,Permission.LEER_TODOS));

    private List<Permission> permisos;

    Role(List<Permission> permisos) {
        this.permisos = permisos;
    }

    public List<Permission> getpermisos() {
        return permisos;
    }

    public void setpermisos(List<Permission> permisos) {
        this.permisos = permisos;
    }

}
