package org.api.service;


import org.api.model.FavoritosEntity;
import org.api.model.FavoritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    @Autowired
    public FavoritosService(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    public void agregarAFavoritos(FavoritosEntity favoritosEntity) {
        try {
            favoritosRepository.save(favoritosEntity);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al agregar a favoritos: " + e.getMessage());
        }
    }

    public void eliminarDeFavoritos(FavoritosEntity favoritosEntity) {
        try {
            favoritosRepository.delete(favoritosEntity);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar de favoritos: " + e.getMessage());
        }
    }
}
