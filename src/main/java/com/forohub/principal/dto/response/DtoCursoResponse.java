package com.forohub.principal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forohub.principal.dto.request.DtoTopico;

import java.util.List;

public record DtoCursoResponse(
        String nombre,
        String categoria,
        @JsonProperty("topicos")
        List<DtoTopicoResponse> topicos
) {
}
