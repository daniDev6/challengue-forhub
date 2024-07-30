package com.forohub.principal.dto;

import com.forohub.principal.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record DtoUsuario(
        String nombre,
        String correoElectronico,
        String password,
        List<DtoRespuesta> dtoRespuestas,
        List<DtoTopico> dtoTopicos,
        List<DtoPerfil> dtoPerfils,
        @Enumerated(EnumType.STRING)
        Role role,
        String username
) {
}
