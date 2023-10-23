package com.afundacion.inazumawiki.opciones;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.afundacion.myaplication.R;


public class FragmentOpciones extends Fragment {

    private static final String TEXT_ID = "text_id";


    public FragmentOpciones() {
        // Required empty public constructor
    }


    public static FragmentOpciones newInstance(@StringRes int textId) {
        FragmentOpciones frag = new FragmentOpciones();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }

    Switch switchNightMode;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opciones, container, false);

        // Inicializa el Switch y establece su estado según las preferencias.
        switchNightMode = view.findViewById(R.id.ModoOscuro);


        // Agrega un Listener para manejar el cambio de estado del Switch.
        switchNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Actualiza el tema de la aplicación en tiempo real.
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreateActivity();
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

