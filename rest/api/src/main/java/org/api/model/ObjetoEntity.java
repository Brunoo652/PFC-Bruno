package org.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = " `objetos` ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 250, nullable = false)
    private String nombre;

    @Column(length = 250)
    private String detalle;

    @Column(length = 250)
    private String sprite;

    @Column
    private boolean favorito;

    // Constructor, getters y setters

    public ObjetoEntity(String nombre, String detalle, String sprite) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.sprite = sprite;

    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "Objeto [id=" + id + ", nombre=" + nombre + ", detalle=" + detalle + ", sprite=" + sprite + "]";
    }
}

