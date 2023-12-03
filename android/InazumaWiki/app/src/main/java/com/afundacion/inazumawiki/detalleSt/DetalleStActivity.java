package com.afundacion.inazumawiki.detalleSt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afundacion.inazumawiki.detalleJugador.DetalleJugadorActivity;
import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.myaplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalleStActivity extends AppCompatActivity {

    private TextView nombreStDetalle;
    private ImageView imagenStDetalle;
    private TextView tipoStDetalle;
    private TextView elementoStDetalle;
    private Button botonFavoritos;
    private Button botonVolverDetalleSt;
    private boolean favorito = false; // Estado inicial: no es favorito


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_st);

        nombreStDetalle = findViewById(R.id.nombreStDetalle);
        imagenStDetalle = findViewById(R.id.imagenStDetalle);
        tipoStDetalle = findViewById(R.id.tipoStDetalle);
        elementoStDetalle = findViewById(R.id.elementoStDetalle);
      //  botonFavoritos = findViewById(R.id.botonFavoritosDetalleSt);
        botonVolverDetalleSt = findViewById(R.id.botonVolverDetalleSt);

        // Obtén la información del jugador aleatorio del intent (por id)
        String stData = getIntent().getStringExtra("st");

        if (stData != null) {
            try {
                JSONObject jsonObject = new JSONObject(stData);
                nombreStDetalle.setText(jsonObject.getString("nombre"));
                Picasso.get().load(jsonObject.getString("sprite")).into(imagenStDetalle);
                tipoStDetalle.setText(jsonObject.getString("tipo"));
                elementoStDetalle.setText(jsonObject.getString("elemento"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

/**************************************************************************************************************/
        //LOGICA DE LOS BOTONES DE VOLVER AL BUSCADOR Y AÑADIR A FAVORITOS


        // Logica boton añadir a favoritos
       /* botonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorito = !favorito; // Cambia el estado

                if (favorito) {
                    // Si es favorito, establece el fondo amarillo
                    botonFavoritos.setBackgroundResource(R.drawable.button_colored);
                    botonFavoritos.setText("Ya añadido");
                } else {
                    // Si no es favorito, establece el fondo gris
                    botonFavoritos.setBackgroundResource(android.R.color.darker_gray);
                    botonFavoritos.setText("Añadir a favoritos");
                }
            }
        });*/

        //Volver al fragmento de busqueda de jugadores
        botonVolverDetalleSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realiza la transacción para volver a la actividad main
                Intent intent = new Intent(DetalleStActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}