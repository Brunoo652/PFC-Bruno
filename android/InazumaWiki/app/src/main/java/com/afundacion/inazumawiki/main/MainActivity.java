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
import com.afundacion.inazumawiki.jugadores.FragmentBuscarJugadoresNombre;
import com.afundacion.inazumawiki.objetos.FragmentBuscarObjetosNombre;
import com.afundacion.inazumawiki.opciones.FragmentOpciones;
import com.afundacion.inazumawiki.st.FragmentBuscarSTNombre;
import com.afundacion.myaplication.R;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    Fragment selectedFragment = new FragmentBuscarJugadoresNombre();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Funcionamiento del NavigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        //Funcionamiento del DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if (id==R.id.BuscadorJugadorNombre){
                    selectedFragment=new FragmentBuscarJugadoresNombre();
                }
                if (item.getItemId() == R.id.BuscadorClubesNombre) {
                    selectedFragment=new FragmentBuscarClubesNombre();
                }
                if (item.getItemId() == R.id.BuscadorClubesNombre) {
                    selectedFragment=new FragmentBuscarClubesNombre();
                }
                if (item.getItemId() == R.id.BuscadorobjetosNombre) {
                    selectedFragment=new FragmentBuscarObjetosNombre();
                }
                if (item.getItemId() == R.id.BuscadorSTNombre) {
                    selectedFragment=new FragmentBuscarSTNombre();
                }
                if (item.getItemId() == R.id.ListaFavoritos) {
                    selectedFragment=new FragmentListaFavoritos();
                }
                if (item.getItemId() == R.id.AcercaDe) {
                    selectedFragment= new FragmentAcercaDe();
                }
                if (item.getItemId() == R.id.Opciones) {
                    selectedFragment=new FragmentOpciones();
                }

                if (selectedFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.BuscadorJugadorNombre,selectedFragment).commit();
                }
                return true;
            }
        });

    }

    //cierre del menú con la pulsación del botón Atrás o back de Android.
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  private int getTitle(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.BuscadorJugadorNombre:
                return R.string.buscadorJugadorNombre;
            case R.id.BuscadorClubesNombre:
                return R.string.buscadorClubesNombre;
            case R.id.BuscadorobjetosNombre:
                return R.string.buscadorObjetosNombre;
            case R.id.BuscadorSTNombre:
                return R.string.buscadorSTNombre;
            case R.id.ListaFavoritos:
                return R.string.listaFavoritos;
            case R.id.AcercaDe:
                return R.string.AcercaDe;
            case R.id.Opciones:
                return R.string.Opciones;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }
    }*/

}