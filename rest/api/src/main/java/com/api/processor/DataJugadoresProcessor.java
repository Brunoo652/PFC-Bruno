package com.api.processor;

import com.api.model.JugadorEntity;
import com.api.sanitize.SanitizeJugador;
import com.api.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataJugadoresProcessor {

    private final JugadorService jugadorService;

    @Autowired
    public DataJugadoresProcessor(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    public void processJugadoresFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jugadorData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("=========================================")) {
                    // Procesar y guardar el jugador cuando se encuentra una línea de separación
                    JugadorEntity jugador = SanitizeJugador.sanitize(jugadorData.toString());
                    if (jugador != null) {
                        jugadorService.saveJugadorToApi(jugador);
                    }
                    jugadorData.setLength(0); // Reiniciar para el próximo jugador
                } else {
                    jugadorData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
