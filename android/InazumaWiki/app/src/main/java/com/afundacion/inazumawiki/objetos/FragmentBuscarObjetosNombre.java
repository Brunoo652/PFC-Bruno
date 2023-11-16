package com.afundacion.inazumawiki.objetos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.objetos.GETAllObjetos;
import com.afundacion.inazumawiki.objetos.GETObjetoPorNombre;
import com.afundacion.inazumawiki.objetos.GetAllObjetosFichaje;
import com.afundacion.inazumawiki.objetos.ObjetoAdapter;
import com.afundacion.myaplication.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FragmentBuscarObjetosNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerViewObjetos;
    private List<Object> objetosList; // Lista que almacena a todos los objetos
    private List<Object> objetosFichajesList; // Lista que almacena todos los objetosFichajes

    public FragmentBuscarObjetosNombre() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_s_t_nombre, container, false);

        // Inicializar la SearchView y el RecyclerView
        searchView = rootView.findViewById(R.id.searchViewST);
        recyclerViewObjetos = rootView.findViewById(R.id.recyclerViewST);

        // Configurar el LinearLayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewObjetos.setLayoutManager(layoutManager);

        // Inicializar la lista de objetos y objetosFichajes
        objetosList = new ArrayList<>();
        objetosFichajesList = new ArrayList<>();

        // Configurar el oyente de búsqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerObjetoPorNombre(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Puedes realizar búsquedas en tiempo real mientras el usuario escribe aquí
                // Actualiza el RecyclerView con los resultados de la búsqueda
                actualizarRecyclerView(newText);
                return true;
            }
        });

        // Realiza una solicitud para obtener todos los objetos y objetosFichajes
        obtenerTodosLosObjetos();
        obtenerTodosLosObjetosFichajes(new GetAllObjetosFichaje.Callback() {
            @Override
            public void onResponse(String result) {
                // Manejar la respuesta aquí si es necesario
                actualizarRecyclerView(searchView.getQuery().toString());
            }

            @Override
            public void onError(Exception e) {
                // Manejar errores aquí
            }
        });

        return rootView;
    }

    // Método para obtener todos los objetos desde la API y configurar el adaptador
    private void obtenerTodosLosObjetos() {
        GETAllObjetos.obtenerTodosLosObjetos(new GETAllObjetos.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray stArray = new JSONArray(result);

                    // Convierte el JSONArray en una lista de objetos
                    objetosList.clear(); // Borra la lista actual
                    for (int i = 0; i < stArray.length(); i++) {
                        objetosList.add(stArray.getJSONObject(i));
                    }

                    // Actualiza el RecyclerView con la lista de objetos actualizada
                    actualizarRecyclerView(searchView.getQuery().toString());

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

    // Método para obtener objetos por nombre desde la API
    private void obtenerObjetoPorNombre(String nombreSt) {
        GETObjetoPorNombre.obtenerDatosObjeto(nombreSt, new GETObjetoPorNombre.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray stArray = new JSONArray(result);

                    // Convierte el JSONArray en una lista de objetos
                    objetosList.clear(); // Borra la lista actual
                    for (int i = 0; i < stArray.length(); i++) {
                        objetosList.add(stArray.getJSONObject(i));
                    }

                    // Actualiza el RecyclerView con la lista de objetos actualizada
                    actualizarRecyclerView(nombreSt);

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

    // Método para obtener todos los objetosFichajes desde la API y configurar el adaptador
    private void obtenerTodosLosObjetosFichajes(GetAllObjetosFichaje.Callback callback) {
        GetAllObjetosFichaje.obtenerTodosLosObjetosFichajes(new GetAllObjetosFichaje.Callback() {
        @Override
        public void onResponse(String result) {
            try {
                JSONArray stArray = new JSONArray(result);

                // Convierte el JSONArray en una lista de objetos
                objetosFichajesList.clear(); // Borra la lista actual
                for (int i = 0; i < stArray.length(); i++) {
                    objetosFichajesList.add(stArray.getJSONObject(i));
                }

                // Actualiza el RecyclerView con la lista de objetosFichajes actualizada
                actualizarRecyclerView(searchView.getQuery().toString());

                // Llama al callback para manejar la respuesta externamente
                callback.onResponse(result);

            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void onError(Exception e) {
            // Manejar errores aquí
            callback.onError(e);
        }
    });
    }

    // Método para actualizar el RecyclerView con la lista filtrada
    private void actualizarRecyclerView(String query) {
        // Combina las listas de objetos y objetosFichajes en una sola lista
        List<Object> combinedList = new ArrayList<>();
        combinedList.addAll(objetosList);
        combinedList.addAll(objetosFichajesList);

        // Filtra la lista combinada según la consulta de búsqueda
        List<Object> filteredList = filtrarObjeto(combinedList, query);

        // Configura el adaptador con la lista filtrada
        ObjetoAdapter adapter = new ObjetoAdapter(filteredList, new ObjetoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object objeto) {

            }

            @Override
            public void onItemClick(JSONObject objeto) {

            }

            @Override
            public void onStClick(Object objeto) {

            }

            @Override
            public void onStClick(JSONObject objeto) {

            }
            // Implementa los métodos según sea necesario
        });
        recyclerViewObjetos.setAdapter(adapter);
    }

    // Método para filtrar objetos por nombre
    private List<Object> filtrarObjeto(List<Object> lista, String textoBusqueda) {
        List<Object> objetoFiltrados = new ArrayList<>();
        for (Object st : lista) {
            if (st instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) st;
                try {
                    String nombre = jsonObject.getString("nombre");
                    if (nombre.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                        objetoFiltrados.add(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return objetoFiltrados;
    }
}

