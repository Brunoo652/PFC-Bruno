package org.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.api.model.ObjetoEntity;
import org.api.model.ObjetoFichajeEntity;
import org.api.proccesor.DataObjetosFichajesProcessor;
import org.api.service.ObjetoFichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "objetoFichaje")
public class ObjetoFichajeController {


    private final ObjetoFichajeService objetoFichajeService;
    private final DataObjetosFichajesProcessor dataObjetosFichajesProccesor;

    @Autowired
    public ObjetoFichajeController(ObjetoFichajeService objetoFichajeService, DataObjetosFichajesProcessor dataObjetosFichajesProccesor) {
        this.objetoFichajeService = objetoFichajeService;
        this.dataObjetosFichajesProccesor = dataObjetosFichajesProccesor;
    }


    @GetMapping("/objetoFichaje/{id}")
    @ApiOperation(value = "Get ObjetoFichaje by ID", notes = "Retrieves an objetoFichaje with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ObjetoFichaje found", response = ObjetoFichajeEntity.class),
            @ApiResponse(code = 400, message = "Invalid ID provided"),
            @ApiResponse(code = 404, message = "ObjetoFichaje not found")
    })
    public ResponseEntity<ObjetoFichajeEntity> getObjetoFichajeById(@PathVariable Integer id) {
        Optional<ObjetoFichajeEntity> objetoFichaje = objetoFichajeService.getObjetoById(id);
        if (objetoFichaje.isPresent()) {
            return ResponseEntity.ok(objetoFichaje.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint que permite buscar a un objetoFichaje por su nombre
    @GetMapping(value = "/objetoFichaje", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get objetoFichaje by name", notes = "Retrieves a objetoFichaje with the specified name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ObjetoFichaje found", response = ObjetoFichajeEntity.class),
            @ApiResponse(code = 404, message = "ObjetoFichaje not found")
    })
    public ResponseEntity<List<ObjetoFichajeEntity>> getObjetoFichajeByNombre(@RequestParam String name) {
        try {
            List<ObjetoFichajeEntity> objetosFichajes = objetoFichajeService.getObjetosFichajeByNombreContaining(name);
            if (objetosFichajes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(objetosFichajes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/procesar-objetos-fichajes")
    @ApiOperation(value = "Procesar objetosFichajes", notes = "Procesa y guarda los objetosFichajes desde el archivo")
    public ResponseEntity<String> procesarObjetosFichajes() {
        String filePath = "C:\\Users\\brure\\Desktop\\infoObjetosFichajes.txt";

        // comprobación para evitar un NullPointerException
        if (dataObjetosFichajesProccesor != null) {
        //    System.out.println("Iniciando procesamiento de objetosFichajes desde el archivo: " + filePath);
            dataObjetosFichajesProccesor.processObjetosFichajesFromFile(filePath);
        //    System.out.println("Procesamiento de objetosFichajes completado");
            return ResponseEntity.ok("Procesamiento de objetosFichajes completado");
        } else {
          //  System.err.println("Error: DataObjetosFichajesProccesor no está inicializado.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: DataObjetosFichajesProccesor no está inicializado.");
        }
    }
}