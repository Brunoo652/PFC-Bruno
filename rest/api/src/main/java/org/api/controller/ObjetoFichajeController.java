package org.api.controller;

import io.swagger.annotations.ApiOperation;
import org.api.model.ObjetoFichajeEntity;
import org.api.proccesor.DataObjetosFichajesProcessor;
import org.api.service.ObjetoFichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("objetos-fichajes")
public class ObjetoFichajeController {


    private final ObjetoFichajeService objetoFichajeService;
    private final DataObjetosFichajesProcessor dataObjetosFichajesProccesor;

    @Autowired
    public ObjetoFichajeController(ObjetoFichajeService objetoFichajeService, DataObjetosFichajesProcessor dataObjetosFichajesProccesor) {
        this.objetoFichajeService = objetoFichajeService;
        this.dataObjetosFichajesProccesor = dataObjetosFichajesProccesor;
    }


    /*@GetMapping("/health")
    @ApiOperation(value = "Check health", notes = "Check the health of the application")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Healthy");
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoFichajeEntity> getObjetoFichajeById(@PathVariable Integer id) {
        Optional<ObjetoFichajeEntity> objeto = objetoFichajeService.getObjetoById(id);
        if (objeto.isPresent()) {
            return ResponseEntity.ok(objeto.get());
        } else {
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
