package com.afundacion.inazumawiki.jugadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.clubes.ClubAdapter;
import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;
import com.afundacion.myaplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.ViewHolder> {
    private List<Object> jugadores;
    private OnItemClickListener listener;

    public JugadorAdapter(List<Object> jugadores, OnItemClickListener listener) {
        this.jugadores = jugadores;
        this.listener = listener;
    }

    public JugadorAdapter(List<Object> clubesList, ClubAdapter.OnItemClickListener onItemClickListener) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object jugador = jugadores.get(position);
        holder.bind(jugador, listener);
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Object jugador);
        void onItemClick(JSONObject jugador);

        void onJugadorClick(Object jugador);

        void onJugadorClick(JSONObject jugador);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageViewSprite;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            imageViewSprite = itemView.findViewById(R.id.imageViewSprite);
        }

        public void bind(final Object jugador, final OnItemClickListener listener) {
            if (jugador instanceof JSONObject) {
                JSONObject jugadorJson = (JSONObject) jugador;
                try {
                    String nombre = jugadorJson.getString("nombre");
                    String urlSprite = jugadorJson.getString("sprite");

                    textViewNombre.setText(nombre);

                    // Cargar la imagen del sprite con Glide
                    Glide.with(itemView)
                            .load(urlSprite)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.logo_app_placeholder) // Manejo de error de carga de imagen
                            .into(imageViewSprite);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(itemView.getContext(), DetalleJugadorActivity.class);
                            intent.putExtra("jugador", jugador.toString());
                            itemView.getContext().startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}