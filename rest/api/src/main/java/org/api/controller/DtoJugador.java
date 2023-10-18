package org.api.controller;


import lombok.Builder;
import lombok.Data;
import org.api.sanitize.SanitizeJugador;


@Data
@Builder
public class DtoJugador extends SanitizeJugador {
    private String nombre;
    private String sprite;
    private String descripcion;
    private String sexo;
    private String afinidad;
    private String posicion;
    private Boolean favorito;
}
