package com.afundacion.inazumawiki.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.afundacion.inazumawiki.acercade.FragmentAcercaDe;
import com.afundacion.inazumawiki.clubes.FragmentBuscarClubesNombre;
import com.afundacion.inazumawiki.favoritos.FragmentListaFavoritos;
import com.afundacion.inazumawiki.home.HomeFragment;
import com.afundacion.inazumawiki.jugadores.FragmentBuscarJugadoresNombre;
import com.afundacion.inazumawiki.objetos.FragmentBuscarObjetosNombre;
import com.afundacion.inazumawiki.opciones.FragmentOpciones;
import com.afundacion.inazumawiki.st.FragmentBuscarSTNombre;
import com.afundacion.myaplication.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selectedFragment = new HomeFragment();

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
                }

                // Cierra el menú de navegación después de hacer una selección
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
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
}
