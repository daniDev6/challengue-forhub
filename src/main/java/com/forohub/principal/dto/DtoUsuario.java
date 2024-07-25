package com.forohub.principal.dto;

import java.util.List;

public record DtoUsuario(
        String nombre,
        String correoElectronico,
        String contrasena,
        List<DtoRespuesta> dtoRespuestas,
        List<DtoTopico> dtoTopicos,
        List<DtoPerfil> dtoPerfils
) {
}
