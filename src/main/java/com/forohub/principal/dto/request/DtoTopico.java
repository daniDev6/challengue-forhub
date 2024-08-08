package com.forohub.principal.dto.request;

import java.time.LocalDate;
import java.util.List;

public record DtoTopico(
        String titulo,
        LocalDate fechaCreacion,
        boolean status,
        Long usuario_id,
        Long curso_id
) {
}
