package com.afundacion.inazumawiki.detalleObjeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afundacion.inazumawiki.detalleClub.DetalleClubActivity;
import com.afundacion.inazumawiki.main.MainActivity;
import com.afundacion.myaplication.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalleObjetoActivity extends AppCompatActivity {

    private TextView nombreObjetoDetalle;
    private TextView detalleObjetoDetalle;

    private TextView nombreObjetoFichjajeDetalle;
    private TextView areaObjetoFichajeDetalle;
    private TextView localizacionObjetoFichajeDetalle;
    private TextView equipoObjetoFichajeDetalle;

    private Button botonFavoritos;
    private Button botonVolverDetalleObjeto;
    private boolean favorito = false; // Estado inicial: no es favorito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_objeto);

        nombreObjetoDetalle = findViewById(R.id.nombreObjetoDetalle);
        detalleObjetoDetalle = findViewById(R.id.detalleObjetoDetalle);
      //  nombreObjetoFichjajeDetalle = findViewById(R.id.nombreObjetoFichajeDetalle);
        areaObjetoFichajeDetalle = findViewById(R.id.areaObjetoFichajeDetalle);
        localizacionObjetoFichajeDetalle = findViewById(R.id.localizacionObjetoFichajeDetalle);
        equipoObjetoFichajeDetalle = findViewById(R.id.equipoObjetoFichajeDetalle);
   //     botonFavoritos = findViewById(R.id.botonFavoritosDetalleObjeto);
        botonVolverDetalleObjeto = findViewById(R.id.botonVolverDetalleObjeto);


        String objeto = getIntent().getStringExtra("objeto");

        if (objeto != null) {
            try {
                JSONObject jsonObject = new JSONObject(objeto);
                nombreObjetoDetalle.setText(jsonObject.getString("nombre"));
            //    nombreObjetoFichjajeDetalle.setText(jsonObject.getString("nombre"));
                areaObjetoFichajeDetalle.setText(jsonObject.getString("area"));
                localizacionObjetoFichajeDetalle.setText(jsonObject.getString("localizacion"));
                equipoObjetoFichajeDetalle.setText(jsonObject.getString("equipo"));
                detalleObjetoDetalle.setText(jsonObject.getString("detalle"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        // Logica boton añadir a favoritos
     /*   botonFavoritos.setOnClickListener(new View.OnClickListener() {
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

        //Volver al fragmento de busqueda de clubes
        botonVolverDetalleObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realiza la transacción para volver a la actividad main
                Intent intent = new Intent(DetalleObjetoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}