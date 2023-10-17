package com.api.service;


import com.api.model.ClubEntity;
import com.api.model.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

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
}
