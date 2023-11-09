package com.afundacion.inazumawiki.opciones;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.afundacion.myaplication.R;

public class FragmentOpciones extends Fragment {

    private Switch switchNightMode;
    private TextView cerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opciones, container, false);

        // Inicializa el Switch y establece su estado según las preferencias.
        switchNightMode = view.findViewById(R.id.ModoOscuro);
        cerrarSesion = view.findViewById(R.id.CerrarSesion);

        // Agrega un Listener para manejar el cambio de estado del Switch.
        switchNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                // Actualiza el tema de la aplicación en tiempo real.
                Toast.makeText(requireContext(), "Opción no disponible aún", Toast.LENGTH_SHORT).show();
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un constructor de AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // Configura el título y el mensaje del diálogo
                builder.setTitle("Cerrar Sesión");
                builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");

                // Agrega un botón positivo (Sí)
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //realizar las acciones necesarias al confirmar el cierre de sesión
                        Toast.makeText(requireContext(), "Opción no disponible aún", Toast.LENGTH_SHORT).show();
                        // Cierra el diálogo
                        dialog.dismiss();
                    }
                });

                // Agrega un botón negativo (No)
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cierra el diálogo
                        dialog.dismiss();
                    }
                });

                // Muestra el diálogo
                builder.show();
            }
        });

        return view;
    }
}