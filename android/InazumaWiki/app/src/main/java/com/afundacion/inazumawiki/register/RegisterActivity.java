package com.afundacion.inazumawiki.register;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afundacion.myaplication.R;

public class RegisterActivity extends AppCompatActivity implements CheckEmail.EmailCheckCallback {

    private EditText emailEditText, password1EditText, password2EditText;
    private Button submitButton;
    private Handler handler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializa las vistas
        emailEditText = findViewById(R.id.EmailRegister);
        password1EditText = findViewById(R.id.RegisterPassword1);
        password2EditText = findViewById(R.id.RegisterPassword2);
        submitButton = findViewById(R.id.SubmitRegisterBoton);

        handler = new Handler(Looper.getMainLooper());

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
                        // Inicia el proceso en segundo plano para verificar el correo
                        new RegisterTask(email).start();
                    } else {
                        // La contraseña no cumple con los requisitos, muestra un toast
                        showToast("La contraseña debe tener entre 8 y 20 caracteres, incluida alguna letra mayúscula", Toast.LENGTH_LONG);
                    }
                } else {
                    // Las contraseñas no coinciden, muestra un toast
                    showToast("Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    public void onEmailCheckComplete(boolean emailExists) throws InterruptedException {
        if (emailExists) {
            // True, el correo existe
            showToast("Este correo ya existe", Toast.LENGTH_SHORT);
        } else {
            // False, el correo no existe, procede con el registro
            String email = emailEditText.getText().toString();
            String password = password1EditText.getText().toString();
            POSTregister.continueRegistration(this, email, password);
        }
    }

    private void showToast(final String message, final int duration) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, message, duration).show();
            }
        });
    }

    private class RegisterTask extends Thread {
        private String email;

        RegisterTask(String email) {
            this.email = email;
        }


        @Override
        public void run() {
            // Realiza la verificación del correo en segundo plano
      //      CheckEmail.isEmailAlreadyRegistered(RegisterActivity.this, email, callback);
        }
    }
}


