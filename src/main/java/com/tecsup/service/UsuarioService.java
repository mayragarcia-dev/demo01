package com.tecsup.service;

import com.tecsup.models.Usuario;
import com.tecsup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}