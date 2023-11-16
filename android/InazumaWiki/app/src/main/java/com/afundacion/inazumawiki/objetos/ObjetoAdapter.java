package com.afundacion.inazumawiki.objetos;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.detalleObjeto.DetalleObjetoActivity;
import com.afundacion.myaplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ObjetoAdapter extends RecyclerView.Adapter<ObjetoAdapter.ViewHolder> {

    private List<Object> objetos;
    private ObjetoAdapter.OnItemClickListener listener;

    public ObjetoAdapter(List<Object> objetos, ObjetoAdapter.OnItemClickListener listener) {
        this.objetos = objetos;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ObjetoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_objeto, parent, false);
        return new ObjetoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjetoAdapter.ViewHolder holder, int position) {
        Object st = objetos.get(position);
        holder.bind(st, listener);
    }


    @Override
    public int getItemCount() {
        return objetos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Object objeto);
        void onItemClick(JSONObject objeto);

        void onStClick(Object objeto);

        void onStClick(JSONObject objeto);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageViewSprite;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            imageViewSprite = itemView.findViewById(R.id.imageViewSprite);
        }

        public void bind(final Object objeto, final ObjetoAdapter.OnItemClickListener listener) {
            if (objeto instanceof JSONObject) {
                JSONObject stJson = (JSONObject) objeto;
                try {
                    String nombre = stJson.getString("nombre");
                    String urlSprite = stJson.getString("sprite");

                    textViewNombre.setText(nombre);

                    // Cargar la imagen del sprite con Glide
                    Glide.with(itemView)
                            .load(urlSprite)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.lupa) // Manejo de error de carga de imagen
                            .into(imageViewSprite);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(itemView.getContext(), DetalleObjetoActivity.class);
                            intent.putExtra("objeto", objeto.toString());
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
