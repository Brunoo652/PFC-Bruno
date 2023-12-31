package org.api.proccesor;

import org.api.sanitize.SanitizeObjetoFichaje;
import org.api.model.ObjetoFichajeEntity;
import org.api.service.ObjetoFichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataObjetosFichajesProcessor {
    private final ObjetoFichajeService objetoFichajeService;


    @Autowired
    public DataObjetosFichajesProcessor(ObjetoFichajeService objetoFichajeService) {
        this.objetoFichajeService = objetoFichajeService;
    }

    public void processObjetosFichajesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder objetoFichajeData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("========================================")) {
                    System.out.println("Found separator line.");
                    // Procesar y guardar el objeto de fichaje cuando se encuentra una línea de separación
                    ObjetoFichajeEntity objetoFichaje = SanitizeObjetoFichaje.sanitizeObjetoFichaje(objetoFichajeData.toString());
                    if (objetoFichaje != null) {
                        objetoFichajeService.saveObjetoFichajeToApi(objetoFichaje);
                    }
                    objetoFichajeData.setLength(0); // Reiniciar para el próximo objeto de fichaje
                } else {
                    objetoFichajeData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}