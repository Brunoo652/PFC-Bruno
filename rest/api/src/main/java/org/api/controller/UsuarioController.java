package org.api.controller;

import io.swagger.annotations.ApiOperation;
import org.api.model.UsuarioEntity;
import org.api.model.UsuarioRepository;
import org.api.service.UsuarioService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }



    //endpoint para el register
    @PostMapping("/register")
    @ApiOperation(value = "Register usuarios", notes = "Almacena en la tabla usuarios los datos introducidos en la pantalla de register")
    public ResponseEntity<JSONObject> registrarUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            // Guarda el usuario en la base de datos
            UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

            if (usuarioGuardado != null) {
                JSONObject response = new JSONObject();
                response.put("message", "Usuario registrado con éxito. ID: " + usuarioGuardado.getId());

                // Devuelve la respuesta como un objeto JSON con código 200 (éxito)
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Maneja el caso donde el usuario no se guarda correctamente con un código 400 (solicitud incorrecta)
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "No se pudo registrar el usuario.");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Registra detalles del error en el log
            e.printStackTrace();
            // Maneja cualquier otro error con un código 500 (error interno del servidor)
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "No se pudo registrar el usuario.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endpoiunt que sirve para comprobar el email, para evitar tener dos cuentas con el mismo email
    @GetMapping("/check-email")
    @ApiOperation(value = "Comprobar email", notes = "Comprueba que el email dado para registrarse no este guardado en la tabla usuarios en la BBDD")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        // Verifica si el correo electrónico ya está registrado en la base de datos
        boolean emailExists = (usuarioRepository.findByEmail(email) != null);
        return ResponseEntity.ok(emailExists);
    }

    //Endpoint de login
    @PostMapping("/login")
    @ApiOperation(value = "Login Usuarios", notes = "Permite comprobar los datos del usuario con los de la tabla para poder acceder a la app")
    public ResponseEntity<String> loginUsuario(@RequestBody Map<String, String> loginData) {
        try {
            // Verifica si el Map contiene las claves "email" y "password"
            if (!loginData.containsKey("email") || !loginData.containsKey("password")) {
                System.out.println(loginData);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud JSON incompleta");
            }
            // Extrae el correo electrónico y la contraseña del Map
            String email = loginData.get("email");
            String password = loginData.get("password");

            // Utiliza el servicio para buscar un usuario por correo electrónico
            UsuarioEntity usuarioEncontrado = usuarioService.findByEmail(email);

            if (usuarioEncontrado != null) {
                // Si se encuentra un usuario, verifica si la contraseña coincide
                if (usuarioEncontrado.getPassword().equals(password)) {
                    // Devuelve una respuesta exitosa (código 200) en caso de inicio de sesión exitoso
                    return ResponseEntity.ok("Inicio de sesión exitoso");
                }
            }

            // Si las credenciales no son válidas, devuelve un error no autorizado (código 401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inicio de sesión fallido");
        } catch (Exception e) {
            // Maneja errores de formato JSON
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud JSON no válida");
        }
    }
}
