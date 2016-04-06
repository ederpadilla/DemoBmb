package eder.padilla.personal.works.redhabitat20.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import eder.padilla.personal.works.redhabitat20.Activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.Modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Eder on 14/03/2016.
 */
public class Fg_Encuesta_Pregunta_Uno extends Fragment implements View.OnClickListener {
    Button btnAceptar, btnNegar;
    Realm realm;
    private RealmResults<Encuesta> allEncuestas;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_ecuesta_pregunta_uno,container,false);
        objectInitialization(view);


        btnAceptar.setOnClickListener(this);
        btnNegar.setOnClickListener(this);

        System.out.println("Repuesta uno es " + ((MainActivity) getActivity()).encuesta.isCubrerequerimientos());


        return view;
    }
    public void objectInitialization(View view){
        btnAceptar =(Button)view.findViewById(R.id.sidelauno);
        btnNegar =(Button)view.findViewById(R.id.nodelauno);
        realm = Realm.getInstance(getActivity().getApplicationContext());
        allEncuestas = realm.where(Encuesta.class).findAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sidelauno:
                ((MainActivity) getActivity()).encuesta.setIdd(allEncuestas.size() + 1);
                ((MainActivity) getActivity()).encuesta.setCubrerequerimientos(true);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(1);
                break;

            case R.id.nodelauno:
                ((MainActivity) getActivity()).encuesta.setIdd(allEncuestas.size() + 1);
                ((MainActivity) getActivity()).encuesta.setCubrerequerimientos(false);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(1);
                break;

        }
    }
}
