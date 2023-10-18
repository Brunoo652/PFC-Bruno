package org.api.service;

import org.api.model.ObjetoFichajeEntity;
import org.api.model.ObjetoFichajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObjetoFichajeService {

    private final ObjetoFichajeRepository objetoFichajeRepository;

    @Autowired
    public ObjetoFichajeService(ObjetoFichajeRepository objetoFichajeRepository) {
        this.objetoFichajeRepository = objetoFichajeRepository;
    }

    public Optional<ObjetoFichajeEntity> getObjetoById(Integer id) {

        return objetoFichajeRepository.findById(id);
    }



    public void saveObjetoFichajeToApi(ObjetoFichajeEntity objetoFichaje) {

        objetoFichajeRepository.save(objetoFichaje);
    }
}
