package com.api.sanitize;

import com.api.model.STEntity;

public class SanitizeST {
    public static STEntity sanitizeST(String stData) {

        STEntity stEntity = new STEntity();

        String[] lines = stData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length != 2) {
                continue; // Salta líneas incorrectas
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            if (value == null || value.isEmpty()) {
                continue; // Skip lines with null or empty values
            }

            switch (key) {
                case "nombre":
                    if (isValidValue(value)) {
                        stEntity.setNombre(value);
                    }
                    break;
                case "sprite":
                    if (isValidValue(value)) {
                        String spritePath = "sprites/" + value.substring(value.lastIndexOf('/') + 1);
                        stEntity.setSprite(spritePath);
                    }
                    break;
                case "tipo":
                    stEntity.setTipo(value);
                    break;
                case "elemento":
                    stEntity.setElemento(value);
                    break;
                case "nombreIngles":
                    stEntity.setNombreIngles(value);
                    break;
            }
        }
        // Check if both "nombre" and "sprite" fields are not null
        if (stEntity.getNombre() == null || stEntity.getSprite() == null) {
            return null;
        }

        return stEntity;

    }

    private static boolean isValidValue(String value) {
        if (value == null || value.isEmpty()) {
            return false; // Value is considered invalid if it's null or empty
        }

        // Additional validation logic can be added here if needed

        return true;
    }
}