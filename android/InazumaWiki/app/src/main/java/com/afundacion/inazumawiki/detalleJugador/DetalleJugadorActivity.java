package com.afundacion.inazumawiki.detalleJugador;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.afundacion.myaplication.R;

public class DetalleJugadorActivity extends AppCompatActivity {

    private ToggleButton botonFavoritos; // Debes declarar el ToggleButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);

        botonFavoritos = findViewById(R.id.botonFavoritosDetalleJugador); // Encuentra el ToggleButton por su ID

        botonFavoritos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Acciones cuando el botón está activado (coloreado)
                    // Por ejemplo, aquí puedes cambiar el estado o realizar otras acciones
                } else {
                    // Acciones cuando el botón está desactivado (sin colorear)
                    // Por ejemplo, aquí puedes cambiar el estado o realizar otras acciones
                }
            }
        });
    }
}
