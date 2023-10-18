package org.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.api.model.JugadorEntity;
import org.api.proccesor.DataJugadoresProccesor;
import org.api.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(tags = "jugador")
public class JugadorController {

    private final JugadorService jugadorService;
    private final DataJugadoresProccesor dataJugadoresProccesor;

    @Autowired
    public JugadorController(JugadorService jugadorService, DataJugadoresProccesor dataJugadoresProccesor) {
        this.jugadorService = jugadorService;
        this.dataJugadoresProccesor = dataJugadoresProccesor;
    }

    //Endpoint que comprueba que la conexion esta establecida
    @GetMapping("/health")
    @ApiOperation(value = "Check health", notes = "Check the health of the application")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Healthy");
    }

    //Endpoint que permite buscar a un jugador por su id
    @GetMapping(value = "/jugador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Jugador by ID", notes = "Retrieves an jugador with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Jugador found", response = JugadorEntity.class),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    public ResponseEntity<JugadorEntity> getJugadorById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(jugadorService.getJugadorById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint que permite buscar a un jugador por su nombre
    @GetMapping(value = "/jugador", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get jugador by name", notes = "Retrieves a jugador with the specified name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Jugador found", response = JugadorEntity.class),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    public ResponseEntity<List<JugadorEntity>> getJugadoresByNombre(@RequestParam String name) {
        try {
            List<JugadorEntity> jugadores = jugadorService.getJugadoresByNombreContaining(name);
            if (jugadores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(jugadores);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint que permite pasar los jugadores a la tabla jugadores
    @GetMapping("/procesar-jugadores")
    @ApiOperation(value = "Procesar jugadores", notes = "Procesa y guarda los jugadores desde el archivo")
    public ResponseEntity<String> procesarJugadores() {
        String filePath = "C:\\Users\\brure\\Desktop\\infoJugadores.txt"; // Ruta completa al archivo

        // comprobación para evitar un NullPointerException
        if (dataJugadoresProccesor != null) {
            dataJugadoresProccesor.processJugadoresFromFile(filePath);
            return ResponseEntity.ok("Procesamiento de jugadores completado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: DataJugadoresProccesor no está inicializado.");
        }
    }

}
