package org.api.service;

import org.api.model.STEntity;
import org.api.model.STRepository;
import org.springframework.stereotype.Service;

@Service
public class STService {

    private final STRepository stRepository;

    public STService(STRepository stRepository) {
        this.stRepository = stRepository;
    }

    public void saveSTToApi (STEntity st){
        stRepository.save(st);
    }
}
