package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Método personalizado para buscar un usuario por su correo electrónico
    UsuarioEntity findByEmail(String email);

}
