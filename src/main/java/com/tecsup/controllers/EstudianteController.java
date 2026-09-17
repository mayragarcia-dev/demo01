package com.tecsup.controllers;

import com.tecsup.models.Curso;
import com.tecsup.models.Estudiante;
import com.tecsup.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Estudiante obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public Estudiante agregarCurso(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId) {

        return service.agregarCurso(estudianteId, cursoId);
    }

    @DeleteMapping("/{estudianteId}/cursos/{cursoId}")
    public Estudiante quitarCurso(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId) {

        return service.quitarCurso(estudianteId, cursoId);
    }

    @GetMapping("/{id}/cursos")
    public Set<Curso> listarCursos(@PathVariable Long id) {
        return service.listarCursos(id);
    }
}