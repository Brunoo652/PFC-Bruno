package com.afundacion.inazumawiki.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.afundacion.inazumawiki.acercade.FragmentAcercaDe;
import com.afundacion.inazumawiki.clubes.FragmentBuscarClubesNombre;
import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;
import com.afundacion.inazumawiki.favoritos.FragmentListaFavoritos;
import com.afundacion.inazumawiki.home.HomeFragment;
import com.afundacion.inazumawiki.jugadores.FragmentBuscarJugadoresNombre;
import com.afundacion.inazumawiki.objetos.FragmentBuscarObjetosNombre;
import com.afundacion.inazumawiki.opciones.FragmentOpciones;
import com.afundacion.inazumawiki.st.FragmentBuscarSTNombre;
import com.afundacion.myaplication.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.BuscadorJugadorNombre) {
                    selectedFragment = new FragmentBuscarJugadoresNombre();
                } else if (id == R.id.BuscadorClubesNombre) {
                    selectedFragment = new FragmentBuscarClubesNombre();
                } else if (id == R.id.BuscadorobjetosNombre) {
                    selectedFragment = new FragmentBuscarObjetosNombre();
                } else if (id == R.id.BuscadorSTNombre) {
                    selectedFragment = new FragmentBuscarSTNombre();
                } else if (id == R.id.ListaFavoritos) {
                    selectedFragment = new FragmentListaFavoritos();
                } else if (id == R.id.AcercaDe) {
                    selectedFragment = new FragmentAcercaDe();
                } else if (id == R.id.Opciones) {
                    selectedFragment = new FragmentOpciones();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

                    // Ocultar o eliminar la vista del jugador aleatorio
                    nombreJugador.setVisibility(View.GONE);
                    spriteJugador.setVisibility(View.GONE);
                }

                // Cierra el menú de navegación después de hacer una selección
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

/***************************************************************************************************************/

        spriteJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la actividad de detalles del jugador aquí
                Intent intent = new Intent(MainActivity.this, DetalleJugadorActivity.class);
                startActivity(intent);
            }
        });

/***************************************************************************************************************/
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarJugadorAleatorio(); // Llama a la función para cargar un jugador aleatorio al iniciar la actividad
    }

    // Controla el comportamiento del botón Atrás o back de Android.
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/****************************************************************************************************/

    // Método para cargar un jugador aleatorio al iniciar la actividad
    private void cargarJugadorAleatorio() {
        GETRandomPlayer.getRandomPlayer(new GETRandomPlayer.RandomPlayerCallback() {
            @Override
            public void onRandomPlayerReceived(String jsonData) {
                // Actualiza la interfaz de usuario con los datos del jugador
                actualizarInterfazUsuario(jsonData);
            }
        });
    }

    // Método para actualizar la interfaz de usuario con los datos del jugador
    private void actualizarInterfazUsuario(String jsonData) {
        try {
            Log.d("MainActivity", "Datos del jugador recibidos: " + jsonData);
            JSONObject jugadorData = new JSONObject(jsonData);
            String nombreDelJugador = jugadorData.getString("nombre");
            String sprite = jugadorData.getString("sprite");

            // Actualiza el TextView con el nombre del jugador
            nombreJugador.setText(nombreDelJugador);

            // Utiliza Picasso para cargar la imagen en el ImageView
            Picasso.get().load(sprite).into(spriteJugador);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

/****************************************************************************************************/

}