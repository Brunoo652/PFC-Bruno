package com.afundacion.inazumawiki.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.afundacion.myaplication.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, password1EditText, password2EditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializa las vistas
        emailEditText = findViewById(R.id.EmailRegister);
        password1EditText = findViewById(R.id.RegisterPassword1);
        password2EditText = findViewById(R.id.RegisterPassword2);
        submitButton = findViewById(R.id.SubmitRegisterBoton);

        // Agrega un OnClickListener al botón "Submit"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los valores de los campos
                String email = emailEditText.getText().toString();
                String password = password1EditText.getText().toString();
                String password2 = password2EditText.getText().toString();

                // Verifica si las contraseñas coinciden
                if (password.equals(password2)) {
                    // Comprueba si la contraseña cumple con los requisitos
                    if (CheckPassword.isPasswordValid(password)) {
                        // La contraseña es válida, proceder a aplicar el metodo register
                        POSTregister.continueRegistration(RegisterActivity.this, email, password);
                    } else {
                        // La contraseña no cumple con los requisitos, muestra un toast
                        Toast.makeText(RegisterActivity.this, "La contraseña debe tener que tener entre 8 y 20 carácteres, incluida alguna letra mayúscula", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Las contraseñas no coinciden, muestra un toast
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}