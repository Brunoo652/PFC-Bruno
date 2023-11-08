package com.afundacion.inazumawiki.jugadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;
import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.myaplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FragmentBuscarJugadoresNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerViewJugadores;
    private List<Object> jugadoresList; // Lista que almacena a todos los jugadores

    public FragmentBuscarJugadoresNombre() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_jugadores_nombre, container, false);

        // Inicializar la SearchView y el RecyclerView
        searchView = rootView.findViewById(R.id.searchView);
        recyclerViewJugadores = rootView.findViewById(R.id.recyclerViewJugadores);

        // Configurar el LinearLayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewJugadores.setLayoutManager(layoutManager);

        // Inicializar la lista de jugadores
        jugadoresList = new ArrayList<>();

        // Configurar el oyente de búsqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerJugadoresPorNombre(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Puedes realizar búsquedas en tiempo real mientras el usuario escribe aquí
                // Actualiza el RecyclerView con los resultados de la búsqueda
                JugadorAdapter adapter = new JugadorAdapter(filtrarJugadores(newText), new JugadorAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Object jugador) {
                        // Manejar la acción de hacer clic en un jugador aquí
                    }

                    @Override
                    public void onItemClick(JSONObject jugador) {
                        // Manejar la acción de hacer clic en un jugador aquí
                    }
                });
                recyclerViewJugadores.setAdapter(adapter);
                return true;
            }
        });

        // Realiza una solicitud para obtener todos los jugadores
        obtenerTodosLosJugadores();

        return rootView;
    }

    // Método para obtener todos los jugadores desde la API y configurar el adaptador
    // Método para obtener todos los jugadores desde la API y configurar el adaptador
    private void obtenerTodosLosJugadores() {
        GETAllJugadores.obtenerTodosLosJugadores(new GETAllJugadores.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray jugadorArray = new JSONArray(result);
                    // Convierte el JSONArray en una lista de objetos
                    jugadoresList.clear(); // Borra la lista actual
                    for (int i = 0; i < jugadorArray.length(); i++) {
                        jugadoresList.add(jugadorArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    JugadorAdapter adapter = new JugadorAdapter(jugadoresList, new JugadorAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Object jugador) {

                        }

                        @Override
                        public void onItemClick(JSONObject jugador) {
                            // Manejar la acción de hacer clic en un jugador aquí
                        }
                    });
                    recyclerViewJugadores.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                // Manejar errores aquí
            }
        });
    }

    // Método para obtener jugadores por nombre desde la API
    private void obtenerJugadoresPorNombre(String nombreJugador) {
        GETJugadorPorNombre.obtenerDatosJugador(nombreJugador, new GETJugadorPorNombre.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray jugadorArray = new JSONArray(result);
                    // Convierte el JSONArray en una lista de objetos
                    jugadoresList.clear(); // Borra la lista actual
                    for (int i = 0; i < jugadorArray.length(); i++) {
                        jugadoresList.add(jugadorArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    JugadorAdapter adapter = new JugadorAdapter(jugadoresList, new JugadorAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Object jugador) {
                            // Manejar la acción de hacer clic en un jugador aquí
                        }

                        @Override
                        public void onItemClick(JSONObject jugador) {
                            // Manejar la acción de hacer clic en un jugador aquí
                        }
                    });
                    recyclerViewJugadores.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                // Manejar errores aquí
            }
        });
    }

    // Método para filtrar jugadores por nombre
    private List<Object> filtrarJugadores(String textoBusqueda) {
        List<Object> jugadoresFiltrados = new ArrayList<>();
        for (Object jugador : jugadoresList) {
            if (jugador instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) jugador;
                try {
                    String nombre = jsonObject.getString("nombre");
                    if (nombre.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                        jugadoresFiltrados.add(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jugadoresFiltrados;
    }
}