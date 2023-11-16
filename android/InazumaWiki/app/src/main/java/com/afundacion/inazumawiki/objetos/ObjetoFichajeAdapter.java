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

public abstract class ObjetoFichajeAdapter extends RecyclerView.Adapter<ObjetoFichajeAdapter.ViewHolder> {

    private List<Object> objetosFichajesList;
    private ObjetoFichajeAdapter.OnItemClickListener listener;

    public ObjetoFichajeAdapter(List<Object> objetosFichajesList, ObjetoFichajeAdapter.OnItemClickListener listener) {
        this.objetosFichajesList = objetosFichajesList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ObjetoFichajeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_objeto, parent, false);
        return new ObjetoFichajeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object objetoFichaje = objetosFichajesList.get(position);
        holder.bind(objetoFichaje, listener);
    }

    @Override
    public int getItemCount() {
        return objetosFichajesList.size();
    }

    public abstract void onItemClick(Object objetoFichaje);

    public abstract void onItemClick(JSONObject objetoFichaje);

    public abstract void onStClick(Object objetoFichaje);

    public abstract void onStClick(JSONObject objetoFichaje);

    public interface OnItemClickListener {
        void onItemClick(Object objetoFichajes);
        void onItemClick(JSONObject objetoFichajes);

        void onStClick(Object objetoFichajes);

        void onStClick(JSONObject objetoFichajes);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageViewSprite;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            imageViewSprite = itemView.findViewById(R.id.imageViewSprite);
        }

        public void bind(final Object objetoFichaje, final ObjetoFichajeAdapter.OnItemClickListener listener) {
            if (objetoFichaje instanceof JSONObject) {
                JSONObject stJson = (JSONObject) objetoFichaje;
                try {
                    String nombre = stJson.getString("nombre");
                    String urlSprite = stJson.getString("sprite");

                    textViewNombre.setText(nombre);

                    // Cargar la imagen del sprite con Glide
                    Glide.with(itemView)
                            .load(urlSprite)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.cofreapp) // Manejo de error de carga de imagen
                            .into(imageViewSprite);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(itemView.getContext(), DetalleObjetoActivity.class);
                            intent.putExtra("objetoFichaje", objetoFichaje.toString());
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
