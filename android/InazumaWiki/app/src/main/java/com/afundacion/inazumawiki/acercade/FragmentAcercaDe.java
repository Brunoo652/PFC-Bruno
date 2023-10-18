package com.afundacion.inazumawiki.acercade;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afundacion.inazumawiki.R;


public class FragmentAcercaDe extends Fragment {

    private static final String TEXT_ID = "text_id";


    public FragmentAcercaDe() {
        // Required empty public constructor
    }


    public static FragmentAcercaDe newInstance(@StringRes int textId) {
        FragmentAcercaDe frag = new FragmentAcercaDe();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_de, container, false);
    }
}