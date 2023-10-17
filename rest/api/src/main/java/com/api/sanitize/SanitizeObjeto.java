package com.api.sanitize;

import com.api.model.ObjetoEntity;

public class SanitizeObjeto {

    public static ObjetoEntity sanitizeObjeto(String objetoData) {
        ObjetoEntity objetoEntity = new ObjetoEntity();

        String[] lines = objetoData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length != 2) {
                continue; // Salta líneas incorrectas
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "nombre":
                    objetoEntity.setNombre(value);
                    break;
                case "detalle":
                    objetoEntity.setDetalle(value);
                    break;

                case "sprite":
                    objetoEntity.setSprite(value);
                    break;
            }

        }


        // Validación additional: Si algún campo importante es nulo o no válido, se omite el jugador
        if (objetoEntity.getNombre() == null || objetoEntity.getNombre().isEmpty() ||
                objetoEntity.getSprite() == null || objetoEntity.getSprite().isEmpty() ||
                objetoEntity.getDetalle() == null || objetoEntity.getDetalle().isEmpty()) { // Cambiado el segundo "null" a "isEmpty"
            return null; // Jugador no válido, se omite
        }

        return objetoEntity;
    }

    public SanitizeObjeto() {
    }
}
