package com.afundacion.inazumawiki.detalleClub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.myaplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalleClubActivity extends AppCompatActivity {


    private TextView nombreClubDetalle;
    private ImageView imagenClubDetalle;
    private TextView descripcionClubDetalle;
    private TextView formacionClubDetalle;
    private TextView miembrosClubDetalle;
    private Button botonFavoritos;
    private Button botonVolverDetalleClub;
    private boolean favorito = false; // Estado inicial: no es favorito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_club);

        nombreClubDetalle = findViewById(R.id.nombreClubDetalle);
        imagenClubDetalle = findViewById(R.id.imagenClubDetalle);
        descripcionClubDetalle = findViewById(R.id.descripcionClubDetalle);
        formacionClubDetalle = findViewById(R.id.formacionClubDetalle);
        miembrosClubDetalle = findViewById(R.id.miembrosClubDetalle);
        botonFavoritos = findViewById(R.id.botonAñadirFavoritosClubDetalle);
        botonVolverDetalleClub = findViewById(R.id.botonVolverMenuClubDetalle);


        String clubData = getIntent().getStringExtra("club");

        if (clubData != null) {
            try {
                JSONObject jsonObject = new JSONObject(clubData);
                nombreClubDetalle.setText(jsonObject.getString("nombre"));
                // Carga la imagen del jugador utilizando Picasso
                Picasso.get().load(jsonObject.getString("sprite")).into(imagenClubDetalle);
                descripcionClubDetalle.setText(jsonObject.getString("descripcion"));
                formacionClubDetalle.setText(jsonObject.getString("formacion"));
                miembrosClubDetalle.setText(jsonObject.getString("miembros"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        // Logica boton añadir a favoritos
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

        //Volver al fragmento de busqueda de clubes
        botonVolverDetalleClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realiza la transacción para volver a la actividad main
                Intent intent = new Intent(DetalleClubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}