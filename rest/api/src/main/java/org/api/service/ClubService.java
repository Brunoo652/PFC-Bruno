package org.api.service;

import org.api.model.ClubEntity;
import org.api.model.ClubRepository;
import org.api.model.JugadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableJpaRepositories
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public void saveClubToApi (ClubEntity club){
        clubRepository.save(club);
    }

    public ClubEntity getClubById(Integer id) {
        return clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Jugador not found"));
    }

    public List<ClubEntity> getClubesByNombreContaining(String nombre) {
        return clubRepository.findByNombreContaining(nombre);
    }
}
