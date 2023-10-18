package org.api.proccesor;

import org.api.model.ObjetoEntity;
import org.api.sanitize.SanitizeObjeto;
import org.api.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataObjetosProcessor {

    private final ObjetoService objetoService;

    @Autowired
    public DataObjetosProcessor(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    public void processObjetosFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder objetoData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("========================================")) {

                    // Procesar y guardar el objeto de fichaje cuando se encuentra una línea de separación
                    ObjetoEntity objetoEntity = SanitizeObjeto.sanitizeObjeto(objetoData.toString());
                    if (objetoEntity != null) {
                        objetoService.saveObjetoToApi(objetoEntity);
                    }
                    objetoData.setLength(0);

                } else {
                    objetoData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

