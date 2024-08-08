package com.forohub.principal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forohub.principal.dto.request.DtoPerfil;
import com.forohub.principal.dto.request.DtoRespuesta;

import com.forohub.principal.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import java.util.List;

public record DtoUsuarioResponse(
        String nombre,
        @JsonProperty("correo")
        String correoElectronico,
        @JsonProperty("respuestas")
        List<DtoRespuesta> dtoRespuestas,
        @JsonProperty("topicos")
        List<DtoTopicoResponse> dtoTopicos,
        @JsonProperty("perfiles")
        List<DtoPerfil> dtoPerfils,
        @Enumerated(EnumType.STRING)
        Role role,
        String username
) {
}
