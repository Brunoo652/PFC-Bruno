package com.afundacion.inazumawiki.favoritos;

import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afundacion.myaplication.R;


public class FragmentListaFavoritos extends Fragment {

    private static final String TEXT_ID = "text_id";


    public FragmentListaFavoritos() {
        // Required empty public constructor
    }


    public static FragmentListaFavoritos newInstance(@StringRes int textId) {
        FragmentListaFavoritos frag = new FragmentListaFavoritos();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_favoritos, container, false);
    }
}