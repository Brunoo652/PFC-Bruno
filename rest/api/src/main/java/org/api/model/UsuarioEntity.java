package org.api.model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UsuarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

 @Column(nullable = false)
  private String token;


  @PrePersist
  public void generateToken() {
    // Los primeros 3 caracteres del token son los 3 primeros dígitos del ID
    String idPart = String.format("%03d", this.id);

    // Los siguientes 20 caracteres son la contraseña o, si es más corta, la contraseña más letras aleatorias
    String passwordPart = this.password.length() >= 20 ? this.password.substring(0, 20) : this.password + generateRandomLetters(20 - this.password.length());

    // Generar dos números aleatorios para los dos últimos caracteres
    Random random = new Random();
    String randomPart = String.format("%02d", random.nextInt(100));

    // Combinar todas las partes para formar el token
    this.token = idPart + passwordPart + randomPart;
  }

  // Método para generar letras aleatorias
  private String generateRandomLetters(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random random = new Random();
    StringBuilder randomLetters = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      randomLetters.append(characters.charAt(random.nextInt(characters.length())));
    }

    return randomLetters.toString();
  }

}

