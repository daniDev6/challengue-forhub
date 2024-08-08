package com.forohub.principal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forohub.principal.dto.request.DtoRespuesta;

import java.time.LocalDate;
import java.util.List;

public record DtoTopicoResponse(
        String titulo,
        LocalDate fechaCreacion,
        boolean status,
        Long usuario_id,
        Long curso_id,
        @JsonProperty("respuestas")
        List<DtoRespuesta> dtoRespuestas
) {
}
