package com.tecsup.service;

import com.tecsup.models.Curso;
import com.tecsup.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repo;

    public Curso guardar(Curso curso) {
        return repo.save(curso);
    }

    public List<Curso> listar() {
        return repo.findAll();
    }

    public Curso obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}
