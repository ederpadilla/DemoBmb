package eder.padilla.personal.works.redhabitat20.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import eder.padilla.personal.works.redhabitat20.Activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;


/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_Encuesta_Pregunta_Ocho extends Fragment implements View.OnClickListener {
    Button btnSiguiente;
    EditText etObjecionparaDescartar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_encuesta_pregunta_ocho,container,false);
        objectInitialization(view);

        btnSiguiente.setOnClickListener(this);
        System.out.println("Repuesta ocho es " + ((MainActivity) getActivity()).encuesta.getObjecionporlaculdescartariaelinmueble());
        return view;
    }
    public void objectInitialization(View view) {
        etObjecionparaDescartar =(EditText)view.findViewById(R.id.descartar);
        btnSiguiente =(Button)view.findViewById(R.id.siguiente);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.siguiente:
                ((MainActivity) getActivity()).encuesta.setObjecionporlaculdescartariaelinmueble(etObjecionparaDescartar.getText().toString());
                ((MainActivity) getActivity()).viewpager.setCurrentItem(8);
                break;
        }
    }
}
