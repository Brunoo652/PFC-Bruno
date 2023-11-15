package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetoRepository extends JpaRepository<ObjetoEntity, Integer> {


    @Query("SELECT o FROM ObjetoEntity o WHERE o.nombre LIKE %:nombre%")
    List<ObjetoEntity> findByNombreContaining(@Param("nombre") String nombre);
}
