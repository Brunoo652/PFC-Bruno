package org.api.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Puedes agregar métodos de consulta personalizados aquí si es necesario
    // Por ejemplo, puedes definir métodos para buscar usuarios por email, etc.

}
