package org.api.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = " `favoritos` ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FavoritosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "elemento_id", nullable = false)
    private Long elementoId;

    @Column(name = "tipo_elemento", nullable = false, length = 50)
    private String tipoElemento;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getElementoId() {
        return elementoId;
    }

    public void setElementoId(Long elementoId) {
        this.elementoId = elementoId;
    }

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }
}
