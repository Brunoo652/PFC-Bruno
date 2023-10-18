package org.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "objetosFichajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjetoFichajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sprite")
    private String sprite;

    @Column(name = "localizacion")
    private String localizacion;

    @Column(name = "area")
    private String area;

    @Column(name = "equipo")
    private String equipo;

    @Column(name = "favorito")
    private Boolean favorito;

    // Constructor, getters y setters

    public ObjetoFichajeEntity(String nombre, String sprite, String localizacion, Boolean favorito, String area, String equipo) {
        this.nombre = nombre;
        this.sprite = sprite;
        this.localizacion = localizacion;
        this.favorito = favorito;
        this.area = area;
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Objeto [id=" + id + ", nombre=" + nombre + ", localizacion=" + localizacion + ", sprite=" + sprite + ", area="+ area +", equipo:"+ equipo +"]";
    }


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSprite() {
        return sprite;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getArea() {
        return area;
    }

    public String getEquipo() {
        return equipo;
    }

    public Boolean getFavorito() {
        return favorito;
    }

}
