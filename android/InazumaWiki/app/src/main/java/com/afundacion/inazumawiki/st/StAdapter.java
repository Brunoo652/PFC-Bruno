package com.afundacion.inazumawiki.st;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afundacion.inazumawiki.detalleSt.DetalleStActivity;
import com.afundacion.myaplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StAdapter extends RecyclerView.Adapter<StAdapter.ViewHolder> {

    private List<Object> sts;
    private StAdapter.OnItemClickListener listener;

    public StAdapter(List<Object> sts, StAdapter.OnItemClickListener listener) {
        this.sts = sts;
        this.listener = listener;
    }


    @NonNull
    @Override
    public StAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_st, parent, false);
        return new StAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object st = sts.get(position);
        holder.bind(st, listener);
    }


    @Override
    public int getItemCount() {
        return sts.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Object st);
        void onItemClick(JSONObject st);

        void onStClick(Object st);

        void onStClick(JSONObject st);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imageViewSprite;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            imageViewSprite = itemView.findViewById(R.id.imageViewSprite);
        }

        public void bind(final Object st, final StAdapter.OnItemClickListener listener) {
            if (st instanceof JSONObject) {
                JSONObject stJson = (JSONObject) st;
                try {
                    String nombre = stJson.getString("nombre");
                    String urlSprite = stJson.getString("sprite");

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
                            Intent intent = new Intent(itemView.getContext(), DetalleStActivity.class);
                            intent.putExtra("st", st.toString());
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
