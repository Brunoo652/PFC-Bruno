package org.api.service;

import org.api.model.UsuarioEntity;
import org.api.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Corrected method to return a single UsuarioEntity
    public UsuarioEntity findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
