package com.forohub.principal.dto.request;

import com.forohub.principal.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;




public record DtoUsuario(
        String nombre,
        String correoElectronico,
        String password,
        @Enumerated(EnumType.STRING)
        Role role,
        String username
) {
}
