package com.afundacion.inazumawiki.jugadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.afundacion.myaplication.R;

import org.json.JSONObject;

import java.util.Collections;

public class FragmentBuscarJugadoresNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;

    public FragmentBuscarJugadoresNombre() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_jugadores_nombre, container, false);

        // Inicializar la SearchView y el RecyclerView
        searchView = rootView.findViewById(R.id.searchView);
        recyclerView = rootView.findViewById(R.id.recyclerViewJugadores);

        // Configurar el oyente de búsqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerJugadoresDesdeAPI(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aquí puedes realizar búsquedas en tiempo real mientras el usuario escribe
                // Por ejemplo, adapter.filter(newText);
                return true;
            }
        });

        return rootView;
    }

    // Método para obtener jugadores desde la API y configurar el adaptador
    private void obtenerJugadoresDesdeAPI(String nombreJugador) {
        // Realiza una solicitud a la API
        String respuestaAPI = GETJugadorPorNombre.obtenerDatosJugador(nombreJugador);

        if (respuestaAPI != null) {
            // Configura el adaptador para el RecyclerView con los datos de respuesta JSON
            JugadorAdapter adapter = new JugadorAdapter(Collections.singletonList(respuestaAPI), new JugadorAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Object jugador) {

                }

                @Override
                public void onItemClick(JSONObject jugador) {
                    // Maneja la acción de hacer clic en un jugador aquí
                }
            });

            recyclerView.setAdapter(adapter);
        }
    }
}
