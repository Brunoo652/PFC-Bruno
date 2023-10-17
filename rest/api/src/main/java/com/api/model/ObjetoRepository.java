package com.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObjetoRepository extends JpaRepository<ObjetoEntity, Integer> {


    @Query("SELECT j FROM ObjetoEntity j WHERE j.nombre LIKE %:nombre%")
    List<ObjetoEntity> findByNombreContaining(@Param("nombre") String nombre);
}