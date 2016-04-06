package eder.padilla.personal.works.redhabitat20.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eder.padilla.personal.works.redhabitat20.Activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;


/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_Encuesta_Pregunta_Siete extends Fragment implements View.OnClickListener {
    Button btnBuenaAtencion, btnAtencionRegular, btnMalaAtencion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_encuesta_pregunta_siete,container,false);
        objectInitialization(view);

        btnBuenaAtencion.setOnClickListener(this);
        btnMalaAtencion.setOnClickListener(this);
        btnAtencionRegular.setOnClickListener(this);

        System.out.println("Repuesta siete es " + ((MainActivity) getActivity()).encuesta.getAtencion());

        return view;
    }
    public void objectInitialization(View view) {
        btnBuenaAtencion =(Button)view.findViewById(R.id.buena);
        btnMalaAtencion =(Button)view.findViewById(R.id.mala);
        btnAtencionRegular =(Button)view.findViewById(R.id.regular);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buena:
                ((MainActivity) getActivity()).encuesta.setAtencion("Buena");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(7);
                break;
            case R.id.regular:
                ((MainActivity) getActivity()).encuesta.setAtencion("Regular");
               ((MainActivity) getActivity()).viewpager.setCurrentItem(7);
                break;
            case R.id.mala:
                ((MainActivity) getActivity()).encuesta.setAtencion("Mala");
                 ((MainActivity) getActivity()).viewpager.setCurrentItem(7);
                break;
        }
    }
}
