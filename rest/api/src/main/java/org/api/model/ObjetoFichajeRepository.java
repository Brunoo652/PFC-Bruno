package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetoFichajeRepository extends JpaRepository <ObjetoFichajeEntity, Integer>{

    @Query("SELECT o FROM ObjetoFichajeEntity o WHERE o.nombre LIKE %:nombre%")
    List<ObjetoFichajeEntity> findByNombreContaining(@Param("nombre") String nombre);
}
