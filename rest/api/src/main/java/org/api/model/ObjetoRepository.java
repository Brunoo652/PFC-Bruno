package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetoRepository extends JpaRepository<ObjetoEntity, Integer> {


    @Query("SELECT j FROM ObjetoEntity j WHERE j.nombre LIKE %:nombre%")
    List<JugadorEntity> findByNombreContaining(@Param("nombre") String nombre);
}
