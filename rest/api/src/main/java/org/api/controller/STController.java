package org.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.api.model.STEntity;
import org.api.proccesor.DataSTProcessor;
import org.api.service.STService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "st")
public class STController {

    private final STService stService;
    private final DataSTProcessor dataSTProcessor;

    @Autowired
    public STController(STService stService, DataSTProcessor dataSTProcessor) {
        this.stService = stService;
        this.dataSTProcessor = dataSTProcessor;
    }


    //Endpoint que permite buscar a un supertécnica por su nombre
    @GetMapping(value = "/st", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get st by name", notes = "Retrieves a st with the specified name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "St found", response = STEntity.class),
            @ApiResponse(code = 404, message = "St not found")
    })
    public ResponseEntity<List<STEntity>> getClubesByNombre(@RequestParam String name) {
        try {
            List<STEntity> clubes = stService.getClubesByNombreContaining(name);
            if (clubes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(clubes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    //endpoint para volcar los datos a la tabla
    @GetMapping("/procesar-st")
    @ApiOperation(value = "Procesar ST", notes = "Procesa y guarda las supertécnicas desde el archivo")
    public ResponseEntity<String> procesarST(){
        String filePath = "C:\\Users\\brure\\Desktop\\informacion_supertecnicas3.txt";

        if (dataSTProcessor != null){
            dataSTProcessor.processSTFromFile(filePath);
            return ResponseEntity.ok("Procesamiento de ST completado");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: DataSTProcessor no está inicializada");
        }
    }

}