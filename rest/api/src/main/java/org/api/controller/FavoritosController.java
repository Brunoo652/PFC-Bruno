package org.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.api.model.FavoritosEntity;
import org.api.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;

    @Autowired
    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    // Endpoint para añadir a favoritos
    @PostMapping("/añadir-favorito")
    @ApiOperation(value = "Añadir a favoritos", notes = "Añade a la tabla de favoritos un elemento")
    public ResponseEntity<String> agregarAFavoritos(@RequestBody FavoritosEntity favoritosEntity) {
        try {
            favoritosService.agregarAFavoritos(favoritosEntity);
            return new ResponseEntity<>("Agregado a favoritos correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agregar a favoritos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para eliminar de favoritos
    @DeleteMapping("/eliminar-favorito")
    @ApiOperation(value = "Eliminar de favoritos", notes = "Elimina un elemento de la tabla de favoritos")
    public ResponseEntity<String> eliminarDeFavoritos(@RequestBody FavoritosEntity favoritosEntity) {
        try {
            favoritosService.eliminarDeFavoritos(favoritosEntity);
            return new ResponseEntity<>("Eliminado de favoritos correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar de favoritos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
