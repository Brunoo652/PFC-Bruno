package com.api.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "clubes")
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String sprite;

    @Column
    private String descripcion;


    @Column
    private String formacion;

    @Column
    private String miembros;

    @Column
    private boolean favorito;


    public ClubEntity(int id, String nombre, String sprite, String descripcion, boolean favorito, String formacion, String miembros) {
        this.id = id;
        this.nombre = nombre;
        this.sprite = sprite;
        this.descripcion = descripcion;
        this.favorito = favorito;
        this.formacion = formacion;
        this.miembros = miembros;
    }

    public ClubEntity() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public void setMiembros(String miembros) {
        this.miembros = miembros;
    }

}
