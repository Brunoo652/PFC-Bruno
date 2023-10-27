package com.afundacion.inazumawiki.pantallaInitial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afundacion.inazumawiki.login.LoginActivity;
import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.inazumawiki.register.RegisterActivity;
import com.afundacion.myaplication.R;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Button loginButton = findViewById(R.id.botonLogin);
        Button registerButton = findViewById(R.id.botonRegister);
        Button inviteButton = findViewById(R.id.botonInvitado);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InitialActivity.this, "Entrando a la aplicación como invitado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
