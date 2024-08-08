package com.forohub.principal.dto.request;

import java.time.LocalDate;

public record DtoRespuesta(
        String mensaje,
        LocalDate fechaCreacion,
        Long usuario_id,
        Long topico_id,
        String solucion
) {
}
