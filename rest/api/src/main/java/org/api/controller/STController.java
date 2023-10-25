package org.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.api.proccesor.DataSTProcessor;
import org.api.service.STService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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