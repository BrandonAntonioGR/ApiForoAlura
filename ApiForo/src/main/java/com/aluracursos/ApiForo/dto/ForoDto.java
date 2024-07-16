package com.aluracursos.ApiForo.dto;

import com.aluracursos.ApiForo.model.Foro;

public record ForoDto(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion
) {
    public ForoDto(Foro foro) {
        this(foro.getId(),foro.getTitulo(),foro.getMensaje(),foro.getFechaCreacion());
    }
}
