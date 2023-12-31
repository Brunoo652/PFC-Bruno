package com.afundacion.inazumawiki.register;

public class CheckPassword {

    public static boolean isPasswordValid(String password) {
        // Comprueba si la contraseña tiene al menos 8 caracteres y no supera los 20 caracteres
        if (password.length() < 8 || password.length() > 20) {
            return false;
        }

        // Comprueba si la contraseña contiene al menos una letra mayúscula
        boolean containsUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
                break;
            }
        }

        return containsUppercase;
    }
}