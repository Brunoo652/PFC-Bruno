package com.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface STRepository extends JpaRepository<STEntity, Integer> {

}
