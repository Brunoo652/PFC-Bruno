package org.api.service;

import org.api.model.JugadorEntity;
import org.api.model.ObjetoEntity;
import org.api.model.ObjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public  ObjetoEntity getObjetoById(Integer id){
        return objetoRepository.findById(id).orElseThrow(() -> new RuntimeException("Objeto not found"));
    }

    public List<ObjetoEntity> getObjetosByNombreContaining(String nombre) {
        return objetoRepository.findByNombreContaining(nombre);
    }
}
