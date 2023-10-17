package com.api.processor;

import com.api.model.STEntity;
import com.api.sanitize.SanitizeST;
import com.api.service.STService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataSTProcessor {

    private final STService stService;

    @Autowired
    public DataSTProcessor(STService stService) {
        this.stService = stService;
    }

    public void processSTFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("==============================")) {
                    System.out.println("salto de linea");
                    // Procesar y guardar el jugador cuando se encuentra una línea de separación
                    STEntity st = SanitizeST.sanitizeST(stData.toString());
                    if (st != null) {
                        stService.saveSTToApi(st);
                    }
                    stData.setLength(0); // Reiniciar para el próximo jugador
                } else {
                    System.out.println("club guardado");
                    stData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
