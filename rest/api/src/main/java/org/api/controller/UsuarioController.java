package org.api.controller;


import org.api.model.UsuarioEntity;
import org.api.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/registrar") // Maneja solicitudes POST en /usuarios/registrar
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            // Guarda el usuario en la base de datos
            UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

            // Puedes personalizar la respuesta si es necesario
            return new ResponseEntity<>("Usuario registrado con éxito. ID: " + usuarioGuardado.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            // Maneja cualquier error que pueda ocurrir al guardar el usuario
            return new ResponseEntity<>("No se pudo registrar el usuario.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
