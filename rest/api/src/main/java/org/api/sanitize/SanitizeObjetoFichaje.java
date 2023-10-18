package org.api.sanitize;

import org.api.model.ObjetoFichajeEntity;

public class SanitizeObjetoFichaje {

    public static ObjetoFichajeEntity sanitizeObjetoFichaje(String objetoFichajeData) {
        ObjetoFichajeEntity objetoFichajeEntity = new ObjetoFichajeEntity();

        String[] lines = objetoFichajeData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length != 2) {
                continue; // Salta líneas incorrectas
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "nombre":
                    objetoFichajeEntity.setNombre(value);
                    break;
                case "area":
                    objetoFichajeEntity.setArea(value);
                    break;
                case "localizacion":
                    objetoFichajeEntity.setLocalizacion(value);
                    break;
                case "equipo":
                    objetoFichajeEntity.setEquipo(value);
                    break;
                case "sprite":
                    objetoFichajeEntity.setSprite(value);
                    break;
            }

        }

        // Validación adicional: Si algún campo importante es nulo o no válido, se omite el jugador
        if (objetoFichajeEntity.getNombre() == null || objetoFichajeEntity.getNombre().isEmpty() ||
                objetoFichajeEntity.getSprite() == null || objetoFichajeEntity.getSprite().isEmpty() ||
                objetoFichajeEntity.getLocalizacion() == null || objetoFichajeEntity.getLocalizacion().isEmpty()) { // Cambiado el segundo "null" a "isEmpty"
            return null; // Jugador no válido, se omite
        }

        return objetoFichajeEntity;
    }

    public SanitizeObjetoFichaje() {
    }
}
