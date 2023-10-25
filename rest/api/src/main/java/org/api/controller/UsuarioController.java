package org.api.controller;

import io.swagger.annotations.ApiOperation;
import org.api.model.UsuarioEntity;
import org.api.model.UsuarioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios") // Define la ruta base para las solicitudes relacionadas con usuarios
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/health")
    @ApiOperation(value = "Check health", notes = "Check the health of the application")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Healthy");
    }


    @PostMapping("/register")
    public ResponseEntity<JSONObject> registrarUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            // Guarda el usuario en la base de datos
            UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

            JSONObject response = new JSONObject();
            response.put("message", "Usuario registrado con éxito. ID: " + usuarioGuardado.getId());

            // Devuelve la respuesta como un objeto JSON
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Registra detalles del error en el log
            e.printStackTrace();
            // Maneja cualquier error que pueda ocurrir al guardar el usuario
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "No se pudo registrar el usuario.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioEntity usuario) {
        // Recupera el usuario de la base de datos por su correo electrónico
        UsuarioEntity usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioEncontrado != null) {
            // Verifica si la contraseña coincide
            if (usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
                // Devuelve una respuesta exitosa (código 200)
                return ResponseEntity.ok("Inicio de sesión exitoso");
            }
        }

        // Si el inicio de sesión no es exitoso, devuelve un error no autorizado (código 401)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inicio de sesión fallido");
    }
}