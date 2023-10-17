package com.api.service;



import com.api.model.ObjetoFichajeEntity;
import com.api.model.ObjetoFichajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@EnableJpaRepositories
@Service
public class ObjetoFichajesService {


    private final ObjetoFichajeRepository objetoFichajeRepository;

    @Autowired
    public ObjetoFichajesService(ObjetoFichajeRepository objetoFichajeRepository) {
        this.objetoFichajeRepository = objetoFichajeRepository;
    }

    public Optional<ObjetoFichajeEntity> getObjetoById(Integer id) {return objetoFichajeRepository.findById(id);}

    public void saveObjetoFichajeToApi(ObjetoFichajeEntity objetoFichaje) {objetoFichajeRepository.save(objetoFichaje);}
}