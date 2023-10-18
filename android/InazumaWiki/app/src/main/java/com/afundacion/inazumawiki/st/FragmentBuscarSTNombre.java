package com.afundacion.inazumawiki.st;

import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afundacion.inazumawiki.R;


public class FragmentBuscarSTNombre extends Fragment {

    private static final String TEXT_ID = "text_id";


    public FragmentBuscarSTNombre() {
        // Required empty public constructor
    }


    public static FragmentBuscarSTNombre newInstance(@StringRes int textId) {
        FragmentBuscarSTNombre frag = new FragmentBuscarSTNombre();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar_s_t_nombre, container, false);
    }
}