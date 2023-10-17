package com.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Integer> {

    @Query("SELECT j FROM JugadorEntity j WHERE j.nombre LIKE %:nombre%")
    List<JugadorEntity> findByNombreContaining(@Param("nombre") String nombre);

}