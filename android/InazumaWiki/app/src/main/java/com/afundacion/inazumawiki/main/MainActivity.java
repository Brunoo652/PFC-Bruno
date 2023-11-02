package com.afundacion.inazumawiki.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;

import com.afundacion.inazumawiki.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.afundacion.myaplication.R;import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fragment selectedFragment;
    private TextView nombreJugador;
    private ImageView spriteJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selectedFragment = new HomeFragment();

        // Encuentra las vistas por sus IDs
        nombreJugador = findViewById(R.id.nombreJugador);
        spriteJugador = findViewById(R.id.spriteJugador);

        // Funcionamiento del NavigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Funcionamiento del DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationDrawerHelper navigationDrawerHelper = new NavigationDrawerHelper(drawerLayout, this, nombreJugador, spriteJugador);

        navigationDrawerHelper.setupNavigationView(navigationView);

        //listener a la imagen, que cuando se clique llevará al detalle del jugador
        spriteJugador.setOnClickListener(view -> {
            Log.d("MainActivity", "Clic en la imagen. Intentando abrir DetalleJugadorActivity.");
            // Crea un Intent para abrir la actividad "DetalleJugadorActivity"
            Intent intent = new Intent(MainActivity.this, DetalleJugadorActivity.class);

            // Puedes pasar datos adicionales a la actividad, si es necesario
            // intent.putExtra("key", value);

            // Inicia la actividad "DetalleJugadorActivity"
            startActivity(intent);
        });
    }
}