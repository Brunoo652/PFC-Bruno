package com.api.service;

import com.api.model.STEntity;
import com.api.model.STRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@EnableJpaRepositories
@Service
public class STService {

    private final STRepository stRepository;

    @Autowired
    public STService(STRepository stRepository) {
        this.stRepository = stRepository;
    }

    public void saveSTToApi (STEntity st){
        stRepository.save(st);
    }
}
