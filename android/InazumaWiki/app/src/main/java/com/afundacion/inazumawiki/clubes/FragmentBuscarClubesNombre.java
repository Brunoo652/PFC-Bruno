package com.afundacion.inazumawiki.clubes;

import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afundacion.inazumawiki.jugadores.GETJugadorPorNombre;
import com.afundacion.inazumawiki.jugadores.JugadorAdapter;
import com.afundacion.myaplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentBuscarClubesNombre extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerViewClubes;
    private List<Object> clubesList; // Lista que almacena a todos los clubes

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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes realizar la solicitud a la API y actualizar el RecyclerView con los resultados.
                obtenerClubesPorNombre(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Puedes realizar búsquedas en tiempo real mientras el usuario escribe aquí
                return false;
            }
        });



        private void obtenerClubesPorNombre(String nombreClub) {
            GETJugadorPorNombre.obtenerDatosJugador(nombreClub, new GETJugadorPorNombre.Callback() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONArray jugadorArray = new JSONArray(result);
                        // Convierte el JSONArray en una lista de objetos
                        clubesList.clear(); // Borra la lista actual
                        for (int i = 0; i < jugadorArray.length(); i++) {
                            clubesList.add(jugadorArray.getJSONObject(i));
                        }

                        // Llena el RecyclerView con la lista de jugadores actualizada
                        JugadorAdapter adapter = new JugadorAdapter(clubesList, new JugadorAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Object jugador) {
                                // Manejar la acción de hacer clic en un jugador aquí
                            }

                            @Override
                            public void onItemClick(JSONObject jugador) {
                                // Manejar la acción de hacer clic en un jugador aquí
                            }
                        });
                        recyclerViewClubes.setAdapter(adapter);
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




        return inflater.inflate(R.layout.fragment_buscar_clubes_nombre, container, false);
    }
}