package com.forohub.principal.dto;

import java.util.List;

public record DtoCurso(
        String nombre,
        String categoria,
        List<DtoTopico> topicos
) {
}
