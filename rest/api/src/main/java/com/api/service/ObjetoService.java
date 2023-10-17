package com.api.service;

import com.api.model.ObjetoEntity;
import com.api.model.ObjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories
@Service
public class ObjetoService {


    private final ObjetoRepository objetoRepository;

    @Autowired
    public ObjetoService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public void saveObjetoToApi(ObjetoEntity objeto) {
        objetoRepository.save(objeto);
    }

    public ObjetoEntity getObjetoById(Integer id){
        return objetoRepository.findById(id).orElseThrow(() -> new RuntimeException("Objeto not found"));
    }
}
