package com.afundacion.inazumawiki.opciones;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.afundacion.myaplication.R;

public class FragmentOpciones extends Fragment {

    private Switch switchNightMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opciones, container, false);

        // Inicializa el Switch y establece su estado según las preferencias.
        switchNightMode = view.findViewById(R.id.ModoOscuro);

        // Agrega un Listener para manejar el cambio de estado del Switch.
        switchNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                // Actualiza el tema de la aplicación en tiempo real.
                int nightMode = isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
                AppCompatDelegate.setDefaultNightMode(nightMode);

                // Retrasa la recreación de la actividad para evitar problemas de parpadeo.
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recreateActivity();
                    }
                }, 100);
            }
        });

        return view;
    }

    private void recreateActivity() {
        if (getActivity() != null) {
            getActivity().recreate();
        }
    }
}


