package com.afundacion.inazumawiki.clubes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;
import com.afundacion.inazumawiki.jugadores.JugadorAdapter;
import com.afundacion.myaplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ClubesAdapter {

    private List<Object> clubes;
    private ClubesAdapter.OnItemClickListener listener;

    public ClubesAdapterAdapter(List<Object> clubes, ClubesAdapter.OnItemClickListener listener) {
        this.clubes = clubes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClubesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorAdapter.ViewHolder holder, int position) {
        Object club = clubes.get(position);
        holder.bind(club, listener);
    }

    @Override
    public int getItemCount() {
        return clubes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Object club);
        void onItemClick(JSONObject club);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombreClubes;
        private ImageView imageViewSpriteClubes;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombreClubes = itemView.findViewById(R.id.textViewNombreClubes);
            imageViewSpriteClubes = itemView.findViewById(R.id.imageViewSpriteClubes);
        }

        public void bind(final Object club, final JugadorAdapter.OnItemClickListener listener) {
            if (club instanceof JSONObject) {
                JSONObject clubJson = (JSONObject) club;
                try {
                    String nombre = clubJson.getString("nombre");
                    String urlSprite = clubJson.getString("sprite");

                    textViewNombreClubes.setText(nombre);

                    // Cargar la imagen del sprite con Glide
                    Glide.with(itemView)
                            .load(urlSprite)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.logo_app_placeholder) // Manejo de error de carga de imagen
                            .into(imageViewSpriteClubes);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(itemView.getContext(), DetalleJugadorActivity.class);
                            intent.putExtra("jugador", club.toString());
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
