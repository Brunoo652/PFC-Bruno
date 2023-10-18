package org.api.model;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = " `supertecnicas` ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class STEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String sprite;

    @Column(length = 250)
    private String tipo;

    @Column(length = 250)
    private String elemento;

    @Column(length = 250)
    private String nombreIngles;

    @Column
    private boolean favorito;

}