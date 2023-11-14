package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface STRepository extends JpaRepository<STEntity, Integer> {

    @Query("SELECT s FROM STEntity s WHERE s.nombre LIKE %:nombre%")
    List<STEntity> findByNombreContaining(@Param("nombre") String nombre);

}
