package com.aluracursos.ApiForo.model;

import com.aluracursos.ApiForo.dto.ForoDto;
import com.aluracursos.ApiForo.dto.ForoModificadoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String fechaCreacion;

    public Foro() {
        // Constructor por defecto
    }

    public Foro(ForoDto foroDto) {
        this.id = foroDto.id();
        this.titulo = foroDto.titulo();
        this.mensaje = foroDto.mensaje();
        this.fechaCreacion = foroDto.fechaCreacion();
    }

    public void actualizarDatos(ForoModificadoDto foroModificadoDto) {
        if (foroModificadoDto.titulo() != null) {
            this.titulo = foroModificadoDto.titulo();
        }
        if (foroModificadoDto.mensaje() != null) {
            this.mensaje = foroModificadoDto.mensaje();
        }
        this.fechaCreacion = obtenerFechaActual();
    }

    private String obtenerFechaActual() {
        return LocalDate.now().toString();
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}