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
import com.afundacion.myaplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {

    private List<Object> clubes;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClubClick(Object club);
        void onClubClick(JSONObject club);

        void onItemClick(Object jugador);

        void onItemClick(JSONObject jugador);
    }


    public ClubAdapter(List<Object> clubes, OnItemClickListener listener) {
        this.clubes = clubes;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ClubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ClubAdapter.ViewHolder holder, int position) {
        Object club = clubes.get(position);
        holder.bind(club, listener);
    }


    @Override
    public int getItemCount() {
        return clubes.size();
    }

    public void updateData(List<Object> newData) {
        clubes.clear();
        clubes.addAll(newData);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombreClubes;
        private ImageView imageViewSpriteClubes;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombreClubes = itemView.findViewById(R.id.textViewNombreClubes);
            imageViewSpriteClubes = itemView.findViewById(R.id.imageViewSpriteClubes);
        }

        public void bind(final Object club, final OnItemClickListener listener) {
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
                          /*  Intent intent = new Intent(itemView.getContext(), DetalleJugadorActivity.class);
                            intent.putExtra("club", club.toString());
                            itemView.getContext().startActivity(intent);*/
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
