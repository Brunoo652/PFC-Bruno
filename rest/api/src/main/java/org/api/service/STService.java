package org.api.service;

import org.api.model.ClubEntity;
import org.api.model.STEntity;
import org.api.model.STRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class STService {

    private final STRepository stRepository;

    public STService(STRepository stRepository) {
        this.stRepository = stRepository;
    }

    public void saveSTToApi (STEntity st){
        stRepository.save(st);
    }

    public List<STEntity> getClubesByNombreContaining(String nombre) {
        return stRepository.findByNombreContaining(nombre);
    }
}
