package com.afundacion.inazumawiki.clubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.afundacion.inazumawiki.jugadores.GETJugadorPorNombre;
import com.afundacion.inazumawiki.jugadores.JugadorAdapter;
import com.afundacion.myaplication.R;
import androidx.appcompat.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FragmentBuscarClubesNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerViewClubes;
    private List<Object> clubesList; // Lista que almacena a todos los clubes
    private ClubAdapter adapter;

    private static final String TEXT_ID = "text_id";

    public FragmentBuscarClubesNombre() {
        // Required empty public constructor
    }

    public static FragmentBuscarClubesNombre newInstance(@StringRes int textId) {
        FragmentBuscarClubesNombre frag = new FragmentBuscarClubesNombre();
        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_clubes_nombre, container, false);

        // Inicializar la SearchView y el RecyclerView
        searchView = rootView.findViewById(R.id.searchViewClubes);
        recyclerViewClubes = rootView.findViewById(R.id.recyclerViewCLubes);

        // Configurar el LinearLayoutManager para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewClubes.setLayoutManager(layoutManager);

        // Inicializar la lista de jugadores
        clubesList = new ArrayList<>();

        // Create an empty adapter and set it to the RecyclerView
        adapter = new ClubAdapter(clubesList, new ClubAdapter.OnItemClickListener() {
            @Override
            public void onClubClick(Object club) {
                // Handle club click
            }

            @Override
            public void onClubClick(JSONObject club) {
                // Handle club click
            }

            @Override
            public void onItemClick(Object jugador) {
                // Handle player click
            }

            @Override
            public void onItemClick(JSONObject jugador) {
                // Handle player click
            }
        });

        recyclerViewClubes.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerClubesPorNombre(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Puedes realizar búsquedas en tiempo real mientras el usuario escribe aquí
                adapter.updateData(filtarClubes(newText));
                return true;
            }
        });
        // Realiza una solicitud para obtener todos los jugadores
        obtenerTodosLosClubes();
        return rootView;
    }

    // Método para filtrar jugadores por nombre
    private List<Object> filtarClubes(String textoBusqueda) {
        List<Object> clubesFiltrados = new ArrayList<>();
        for (Object club : clubesList) {
            if (club instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) club;
                try {
                    String nombre = jsonObject.getString("nombre");
                    if (nombre.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                        clubesFiltrados.add(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return clubesFiltrados;
    }

    private void obtenerClubesPorNombre(String nombreClub) {
        GETClubPorNombre.obtenerDatosJugador(nombreClub, new GETClubPorNombre.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray clubArray = new JSONArray(result);
                    // Convierte el JSONArray en una lista de objetos
                    clubesList.clear(); // Borra la lista actual
                    for (int i = 0; i < clubArray.length(); i++) {
                        clubesList.add(clubArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    adapter.notifyDataSetChanged();
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

    private void obtenerTodosLosClubes() {
        GETAllClubes.obtenerTodosLosClubes(new GETAllClubes.Callback() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONArray clubArray = new JSONArray(result);
                    // Convierte el JSONArray en una lista de objetos
                    clubesList.clear(); // Borra la lista actual
                    for (int i = 0; i < clubArray.length(); i++) {
                        clubesList.add(clubArray.getJSONObject(i));
                    }

                    // Llena el RecyclerView con la lista de jugadores actualizada
                    adapter.notifyDataSetChanged();
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
}