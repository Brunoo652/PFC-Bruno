package com.afundacion.inazumawiki.detalleJugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.afundacion.inazumawiki.main.MainActivity;
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
    private Button botonVolverDetalleJugador;
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
       // botonFavoritos = findViewById(R.id.botonFavoritosDetalleJugador);
        botonVolverDetalleJugador = findViewById(R.id.botonVolverDetalleJugador);
/**************************************************************************************************************/
        // Obtén la información del jugador aleatorio del intent (por id)
        String jugadorDataRandom = getIntent().getStringExtra("jugadorDataRandom");

        if (jugadorDataRandom != null) {
            try {
                JSONObject jsonObject = new JSONObject(jugadorDataRandom);
                nombreJugadorDetalle.setText(jsonObject.getString("nombre"));
                Picasso.get().load(jsonObject.getString("sprite")).into(imagenJugadorDetalle);
                descripcionJugadorDetalle.setText(jsonObject.getString("descripcion"));
                sexoJugadorDetalle.setText(jsonObject.getString("sexo"));
                afinidadJugadorDetalle.setText(jsonObject.getString("afinidad"));
                posicionJugadorDetalle.setText(jsonObject.getString("posicion"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
/**************************************************************************************************************/

/**************************************************************************************************************/
        // Obtén la información del jugador del fragment de Buscar  Jugadores (por nombre)

        String jugadorData = getIntent().getStringExtra("jugador");

        if (jugadorData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jugadorData);
                nombreJugadorDetalle.setText(jsonObject.getString("nombre"));
                // Carga la imagen del jugador utilizando Picasso
                Picasso.get().load(jsonObject.getString("sprite")).into(imagenJugadorDetalle);
                descripcionJugadorDetalle.setText(jsonObject.getString("descripcion"));
                sexoJugadorDetalle.setText(jsonObject.getString("sexo"));
                afinidadJugadorDetalle.setText(jsonObject.getString("afinidad"));
                posicionJugadorDetalle.setText(jsonObject.getString("posicion"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
/**************************************************************************************************************/

/**************************************************************************************************************/
        //LOGICA DE LOS BOTONES DE VOLVER AL BUSCADOR Y AÑADIR A FAVORITOS


        // Logica boton añadir a favoritos
      /*  botonFavoritos.setOnClickListener(new View.OnClickListener() {
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
        botonVolverDetalleJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realiza la transacción para volver a la actividad main
                Intent intent = new Intent(DetalleJugadorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}