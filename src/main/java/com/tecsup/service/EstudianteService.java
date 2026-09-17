package com.tecsup.service;

import com.tecsup.models.Curso;
import com.tecsup.models.Estudiante;
import com.tecsup.repository.CursoRepository;
import com.tecsup.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CursoRepository cursoRepo;

    public Estudiante guardar(Estudiante estudiante) {

        Set<Curso> cursos = estudiante.getCursos();

        if (cursos != null) {
            for (Curso curso : cursos) {
                Curso cursoBD = cursoRepo.findById(curso.getId())
                        .orElseThrow(() -> new RuntimeException("Curso no existe"));

                curso.setId(cursoBD.getId());
                curso.setNombre(cursoBD.getNombre());
                curso.setCreditos(cursoBD.getCreditos());
            }
        }

        return estudianteRepo.save(estudiante);
    }

    public List<Estudiante> listar() {
        return estudianteRepo.findAll();
    }

    public Estudiante obtener(Long id) {
        return estudianteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Estudiante agregarCurso(Long estudianteId, Long cursoId) {

        Estudiante estudiante = obtener(estudianteId);

        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no existe"));

        if (estudiante.getCursos().contains(curso)) {
            throw new RuntimeException("El estudiante ya está inscrito en este curso");
        }

        estudiante.getCursos().add(curso);

        return estudianteRepo.save(estudiante);
    }

    public Estudiante quitarCurso(Long estudianteId, Long cursoId) {

        Estudiante estudiante = obtener(estudianteId);

        estudiante.getCursos()
                .removeIf(curso -> curso.getId().equals(cursoId));

        return estudianteRepo.save(estudiante);
    }

    public Set<Curso> listarCursos(Long estudianteId) {

        Estudiante estudiante = obtener(estudianteId);

        return estudiante.getCursos();
    }
}
