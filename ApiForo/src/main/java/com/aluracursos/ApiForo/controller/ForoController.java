package com.aluracursos.ApiForo.controller;

import com.aluracursos.ApiForo.dto.ForoDto;
import com.aluracursos.ApiForo.dto.ForoModificadoDto;
import com.aluracursos.ApiForo.model.Foro;
import com.aluracursos.ApiForo.repository.ForoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class ForoController {

    @Autowired
    private ForoRepository foroRepository;

    @PostMapping
    public ResponseEntity<ForoDto> registrarForo(@RequestBody @Valid ForoDto datosRegistroMedico,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        Foro foro = foroRepository.save(new Foro(datosRegistroMedico));
        ForoDto datosRespuestaMedico = new ForoDto(foro);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(foro.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    @GetMapping
    public ResponseEntity<Page<ForoDto>> listadoForos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(foroRepository.findAll(paginacion).map(ForoDto::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarForo(@RequestBody @Valid ForoModificadoDto datosActualizarForo) {
        Foro foro = foroRepository.getReferenceById(datosActualizarForo.id());
        foro.actualizarDatos(datosActualizarForo);
        return ResponseEntity.ok(foro);
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarForo(@PathVariable Long id) {
        Foro medico = foroRepository.getReferenceById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForoDto> retornaDatosForo(@PathVariable Long id) {
        Foro foro = foroRepository.getReferenceById(id);
        var datosMedico = new ForoDto(foro);
        return ResponseEntity.ok(datosMedico);
    }

}
