package org.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.api.model.JugadorEntity;
import org.api.model.ObjetoEntity;
import org.api.proccesor.DataObjetosProcessor;
import org.api.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "objeto")
public class ObjetoController {

    private ObjetoService objetoService;
    private ObjetoEntity objetoEntity;
    private DataObjetosProcessor dataObjetosProcessor;

    @Autowired
    public ObjetoController(DataObjetosProcessor dataObjetosProcessor, ObjetoService objetoService) {
        this.dataObjetosProcessor = dataObjetosProcessor;
        this.objetoService = objetoService;
    }


    @GetMapping("/procesar-objetos")
    @ApiOperation(value = "Procesar objetos", notes = "Procesa y guarda los objetos desde el archivo")
    public ResponseEntity<String> procesarObjetos() {
        String filePath = "C:\\Users\\brure\\Desktop\\infoObjetos.txt";

        if (dataObjetosProcessor != null) {
            dataObjetosProcessor.processObjetosFromFile(filePath);
            return ResponseEntity.ok("Procesamiento de objetos completado");
        } else {
            System.err.println("Error: DataObjetosProcessor no está inicializado.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: DataObjetosProcessor no está inicializado.");
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Objeto by ID", notes = "Retrieves an objeto with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Objeto found", response = ObjetoEntity.class),
            @ApiResponse(code = 400, message = "Invalid ID provided"),
            @ApiResponse(code = 404, message = "Objeto not found")
    })
    public ResponseEntity<ObjetoEntity> getObjetoById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(objetoService.getObjetoById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint que permite buscar a un objeto por su nombre
    @GetMapping(value = "/objeto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get objeto by name", notes = "Retrieves a objeto with the specified name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Objeto found", response = ObjetoEntity.class),
            @ApiResponse(code = 404, message = "Objeto not found")
    })
    public ResponseEntity<List<ObjetoEntity>> getObjetoByNombre(@RequestParam String name) {
        try {
            List<ObjetoEntity> objetos = objetoService.getObjetosByNombreContaining(name);
            if (objetos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(objetos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}