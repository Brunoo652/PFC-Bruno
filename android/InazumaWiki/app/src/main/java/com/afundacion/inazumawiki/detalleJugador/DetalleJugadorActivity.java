package com.afundacion.inazumawiki.detalleJugador;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.afundacion.myaplication.R;
import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;


public class DetalleJugadorActivity extends AppCompatActivity {

    private TextView nombreJugadorDetalle;
    private ImageView imagenJugadorDetalle;
    private TextView descripcionJugadorDetalle;
    private TextView sexoJugadorDetalle;
    private TextView afinidadJugadorDetalle;
    private TextView posicionJugadorDetalle;
    private Button botonFavoritos;
    private boolean favorito = false; // Estado inicial: no es favorito


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);

        nombreJugadorDetalle = findViewById(R.id.nombreJugadorDetalle);
        imagenJugadorDetalle = findViewById(R.id.imagenJugadorDetalle);
        descripcionJugadorDetalle = findViewById(R.id.descripcionJugadorDetalle);
        sexoJugadorDetalle = findViewById(R.id.sexoJugadorDetalle);
        afinidadJugadorDetalle = findViewById(R.id.afinidadJugadorDetalle);
        posicionJugadorDetalle = findViewById(R.id.posicionJugadorDetalle);
        botonFavoritos = findViewById(R.id.botonFavoritosDetalleJugador);
/**************************************************************************************************************/
        // Obtén la información del jugador aleatorio del intent
        String jugadorData = getIntent().getStringExtra("jugador_data");


        if ( jugadorData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jugadorData);
                nombreJugadorDetalle.setText(jsonObject.getString("nombre"));
                descripcionJugadorDetalle.setText(jsonObject.getString("descripcion"));
                sexoJugadorDetalle.setText(jsonObject.getString("sexo"));
                afinidadJugadorDetalle.setText(jsonObject.getString("afinidad"));
                posicionJugadorDetalle.setText(jsonObject.getString("posicion"));

                // La imagen del jugador generalmente se almacena como una URL en la base de datos.
                // Puedes utilizar una biblioteca de carga de imágenes como Glide o Picasso para mostrar la imagen.
                String imagenUrl = jsonObject.getString("sprite");
                Picasso.get().load(imagenUrl).into(imagenJugadorDetalle);
                Log.d("DetalleJugadorActivity", "Datos recibidos: " + jsonObject.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
/**************************************************************************************************************/
        botonFavoritos.setOnClickListener(new View.OnClickListener() {
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
        });
    }
}
