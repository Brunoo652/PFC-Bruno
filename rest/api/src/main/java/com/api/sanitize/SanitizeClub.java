package com.api.sanitize;

import com.api.model.ClubEntity;

public class SanitizeClub {
    public static ClubEntity sanitize(String clubData) {
        if (clubData == null || clubData.isEmpty()) {
            return null; // Cannot process a null or empty object
        }

        ClubEntity clubEntity = new ClubEntity();
        String[] lines = clubData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length != 2) {
                continue; // Skip lines with incorrect format
            }

            String key = parts[0].trim();
            String value = parts[1].trim();

            if (value == null || value.isEmpty()) {
                continue; // Skip lines with null or empty values
            }

            switch (key) {
                case "nombre":
                    if (isValidValue(value)) {
                        clubEntity.setNombre(value);
                    }
                    break;
                case "sprite":
                    if (isValidValue(value)) {
                        String spritePath = "sprites/" + value.substring(value.lastIndexOf('/') + 1);
                        clubEntity.setSprite(spritePath);
                    }
                    break;
                case "descripcion":
                    clubEntity.setDescripcion(value);
                    break;
                case "formacion":
                    clubEntity.setFormacion(value);
                    break;
                case "miembros":
                    clubEntity.setMiembros(value);
                    break;
                default:
                    // Other keys not relevant, handle if necessary
                    break;
            }
        }

        // Check if both "nombre" and "sprite" fields are not null
        if (clubEntity.getNombre() == null || clubEntity.getSprite() == null) {
            return null;
        }

        return clubEntity;
    }

    // Method to validate if a value is valid
    private static boolean isValidValue(String value) {
        if (value == null || value.isEmpty()) {
            return false; // Value is considered invalid if it's null or empty
        }

        // Additional validation logic can be added here if needed

        return true;
    }
}