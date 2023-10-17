package com.api.service;

import com.api.model.JugadorEntity;
import com.api.model.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories
@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    @Autowired
    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public JugadorEntity getJugadorById(Integer id) {
        return jugadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Jugador not found"));
    }

    public List<JugadorEntity> getJugadoresByNombreContaining(String nombre) {
        return jugadorRepository.findByNombreContaining(nombre);
    }

    public void saveJugadorToApi(JugadorEntity jugador) {
        jugadorRepository.save(jugador);
    }

}