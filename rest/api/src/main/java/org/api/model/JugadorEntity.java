package org.api.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = " `jugadores` ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JugadorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, name = "nombre")
  private String nombre;

  @Column(nullable = false)
  private String sprite;

  @Column(length = 500)
  private String descripcion;

  @Column(length = 1)
  private String sexo;

  @Column(length = 10)
  private String afinidad;

  @Column(length = 15)
  private String posicion;

  private boolean favorito;

}
