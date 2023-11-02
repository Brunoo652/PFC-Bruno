package com.afundacion.inazumawiki.main;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NavigationDrawerHelper {
    private DrawerLayout drawerLayout;
    private AppCompatActivity activity;

    private Fragment selectedFragment;
    private TextView nombreJugador;
    private ImageView spriteJugador;

    public NavigationDrawerHelper(DrawerLayout drawerLayout, AppCompatActivity activity, TextView nombreJugador, ImageView spriteJugador) {
        this.drawerLayout = drawerLayout;
        this.activity = activity;
        this.nombreJugador = nombreJugador;
        this.spriteJugador = spriteJugador;
    }

    public void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.BuscadorJugadorNombre:
                selectedFragment = new FragmentBuscarJugadoresNombre();
                break;
            case R.id.BuscadorClubesNombre:
                selectedFragment = new FragmentBuscarClubesNombre();
                break;
            case R.id.BuscadorobjetosNombre:
                selectedFragment = new FragmentBuscarObjetosNombre();
                break;
            case R.id.BuscadorSTNombre:
                selectedFragment = new FragmentBuscarSTNombre();
                break;
            case R.id.ListaFavoritos:
                selectedFragment = new FragmentListaFavoritos();
                break;
            case R.id.AcercaDe:
                selectedFragment = new FragmentAcercaDe();
                break;
            case R.id.Opciones:
                selectedFragment = new FragmentOpciones();
                break;
        }

        if (selectedFragment != null) {
            replaceFragment(selectedFragment);
            hideRandomPlayerView();
        }

        closeDrawer();

        return true;
    }

    private void replaceFragment(Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }

    private void hideRandomPlayerView() {
        nombreJugador.setVisibility(View.GONE);
        spriteJugador.setVisibility(View.GONE);
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
