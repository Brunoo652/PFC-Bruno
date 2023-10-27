package com.afundacion.inazumawiki.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.inazumawiki.register.CheckEmail;
import com.afundacion.myaplication.R;

public class LoginActivity extends AppCompatActivity implements POSTlogin.LoginCallback {

    private EditText emailEditText, passwordEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.EmailLogin);
        passwordEditText = findViewById(R.id.LoginPassword);
        submitButton = findViewById(R.id.SubmitLoginBoton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

              //  CheckEmailLogin.isEmailExisting(LoginActivity.this, email, CheckEmail.CheckEmailLoginCallback callback);
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailError() {
        Toast.makeText(LoginActivity.this, "Correo electrónico incorrecto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordError() {
        Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError() {
        Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
    }
}
