package com.afundacion.inazumawiki.st;

import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afundacion.inazumawiki.jugadores.GETAllJugadores;
import com.afundacion.inazumawiki.jugadores.GETJugadorPorNombre;
import com.afundacion.inazumawiki.jugadores.JugadorAdapter;
import com.afundacion.myaplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentBuscarSTNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerViewSt;
    private List<Object> stList; // Lista que almacena a todas las st

    public FragmentBuscarSTNombre() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_s_t_nombre, container, false);

        // Inicializar la SearchView y el RecyclerView
        searchView = rootView.findViewById(R.id.searchViewST);
        recyclerViewSt = rootView.findViewById(R.id.recyclerViewST);

        // Configurar el LinearLayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSt.setLayoutManager(layoutManager);

        // Inicializar la lista de jugadores
        stList = new ArrayList<>();

        // Configurar el oyente de búsqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerStPorNombre(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Puedes realizar búsquedas en tiempo real mientras el usuario escribe aquí
                // Actualiza el RecyclerView con los resultados de la búsqueda
                StAdapter adapter = new StAdapter(filtrarSt(newText), new StAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(Object st) {

                    }

                    @Override
                    public void onItemClick(JSONObject st) {

                    }

                    @Override
                    public void onStClick(Object st) {

                    }

                    @Override
                    public void onStClick(JSONObject st) {

                    }
                });
                recyclerViewSt.setAdapter(adapter);
                return true;
            }
        });

        // Realiza una solicitud para obtener todos los jugadores
        obtenerTodasLasSts();

        return rootView;
    }

    // Método para obtener todos los jugadores desde la API y configurar el adaptador
    private void obtenerTodasLasSts() {
        GETAllSt.obtenerTodasLasSt(new GETAllSt.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray stArray = new JSONArray(result);

                    // Convierte el JSONArray en una lista de objetos
                    stList.clear(); // Borra la lista actual

                    for (int i = 0; i < stArray.length(); i++) {
                        stList.add(stArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    StAdapter adapter = new StAdapter(stList, new StAdapter.OnItemClickListener() {

                        @Override
                        public void onItemClick(Object st) {

                        }

                        @Override
                        public void onItemClick(JSONObject st) {

                        }

                        @Override
                        public void onStClick(Object st) {

                        }

                        @Override
                        public void onStClick(JSONObject st) {

                        }
                    });
                    recyclerViewSt.setAdapter(adapter);

                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void onError(Exception e) {
                // Manejar errores aquí
            }
        });
    }

    // Método para obtener jugadores por nombre desde la API
    private void obtenerStPorNombre(String nombreSt) {
        GETStPorNombre.obtenerDatosSt(nombreSt, new GETStPorNombre.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray stArray = new JSONArray(result);
                    // Convierte el JSONArray en una lista de objetos
                    stList.clear(); // Borra la lista actual
                    for (int i = 0; i < stArray.length(); i++) {
                        stList.add(stArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    StAdapter adapter = new StAdapter(stList, new StAdapter.OnItemClickListener() {


                        @Override
                        public void onItemClick(Object st) {

                        }

                        @Override
                        public void onItemClick(JSONObject st) {

                        }

                        @Override
                        public void onStClick(Object st) {

                        }

                        @Override
                        public void onStClick(JSONObject st) {

                        }
                    });
                    recyclerViewSt.setAdapter(adapter);
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
    private List<Object> filtrarSt(String textoBusqueda) {
        List<Object> stFiltrados = new ArrayList<>();
        for (Object st : stList) {
            if (st instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) st;
                try {
                    String nombre = jsonObject.getString("nombre");
                    if (nombre.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                        stFiltrados.add(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return stFiltrados;
    }
}