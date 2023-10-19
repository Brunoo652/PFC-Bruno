package com.afundacion.inazumawiki.acercade;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afundacion.myaplication.R;


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

    @SuppressLint("MissingInflatedId")
    public View onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container) {
        super.onViewCreated(view, savedInstanceState);

        View layout = inflater.inflate(R.layout.fragment_acerca_de, container, false);

        if (getArguments() != null) {
            String text = getString(getArguments().getInt(TEXT_ID));
            ((TextView) layout.findViewById(R.id.TextoAcercaDe)).setText(text);
        } else {
            throw new IllegalArgumentException("Argument " + TEXT_ID + " is mandatory");
        }

        return layout;
    }

}