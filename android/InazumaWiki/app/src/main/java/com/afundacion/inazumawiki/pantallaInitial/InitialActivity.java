package com.afundacion.inazumawiki.pantallaInitial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afundacion.inazumawiki.MainActivity;
import com.afundacion.myaplication.R;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Button loginButton = findViewById(R.id.botonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para abrir la actividad MainActivity
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}