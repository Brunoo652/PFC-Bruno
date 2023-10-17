package com.api.controller;


import com.api.model.ClubEntity;
import com.api.processor.DataClubesProcessor;
import com.api.service.ClubService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clubes")
public class ClubController {

    private final ClubService clubService;
    private final DataClubesProcessor dataClubesProcessor;

    @Autowired
    public ClubController(ClubService clubService, DataClubesProcessor dataClubesProcessor) {
        this.clubService = clubService;
        this.dataClubesProcessor = dataClubesProcessor;
    }


    //Endpoint que permite buscar a un club por su id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Club by ID", notes = "Retrieves an CLub with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Club found", response = ClubEntity.class),
            @ApiResponse(code = 404, message = "Club not found")
    })
    public ResponseEntity<ClubEntity> getJugadorById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(clubService.getClubById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }


    /*
    //Endpoint para volcar los datos de los clubes a la BBDD USAR SOLO UNA VEZ PARA EVITAR DUPLICADOS
        @GetMapping("/procesar-clubes")
    @ApiOperation(value = "Procesar Clubes", notes = "Procesa y guarda en la bbdd los clubes desde el archivo")
    public ResponseEntity<String> procesarClubes() throws IOException {
        String filepath = "C:\\Users\\brure\\Desktop\\infoClubes.txt";

        // comprobación para evitar un NullPointerException
        if (dataClubesProcessor != null) {
            dataClubesProcessor.processClubesFromFile(filepath);
            return ResponseEntity.ok("Procesamiento de clubes completado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: DataClubesProcessor no está inicializado.");
        }
    }

     */
}
