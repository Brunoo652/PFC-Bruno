package com.api.sanitize;

import com.api.model.JugadorEntity;

public class SanitizeJugador {
    public static JugadorEntity sanitize(String jugadorData) {
        JugadorEntity jugadorEntity = new JugadorEntity();

        String[] lines = jugadorData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length != 2) {
                continue; // Salta líneas incorrectas
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "nombre":
                    jugadorEntity.setNombre(value);
                    break;
                case "sprite":
                    String spritePath = "sprites/" + value.substring(value.lastIndexOf('/') + 1);
                    jugadorEntity.setSprite(spritePath);
                    break;
                case "descripción":
                    jugadorEntity.setDescripcion(value);
                    break;
                case "sexo":
                    switch (value) {
                        case "M":
                        case "Masculino":
                            jugadorEntity.setSexo("Masculino");
                            break;
                        case "F":
                        case "Femenino":
                            jugadorEntity.setSexo("Femenino");
                            break;
                        default:
                            continue; // Sexo no válido, se omite el jugador
                    }
                    break;
                case "afinidad":
                    switch (value) {
                        case "Montaña":
                        case "Fuego":
                        case "Aire":
                        case "Bosque":
                        case "Neutro":
                            jugadorEntity.setAfinidad(value);
                            break;
                        default:
                            continue; // Afinidad no válida, se omite el jugador
                    }
                    break;
                case "posición":
                    jugadorEntity.setPosicion(value);
                    break;
                default:
                    // Otras claves no relevantes, poner aqui si fuese necesario
                    break;
            }
        }

        // Validación adicional: Si algún campo importante es nulo o no válido, se omite el jugador
        if (jugadorEntity.getNombre() == null || jugadorEntity.getNombre().isEmpty() ||
                jugadorEntity.getSprite() == null || jugadorEntity.getSprite().isEmpty() ||
                jugadorEntity.getSexo() == null || jugadorEntity.getAfinidad() == null) {
            return null; // Jugador no válido, se omite
        }

        return jugadorEntity;
    }

    public SanitizeJugador() {
    }

}