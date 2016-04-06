package eder.padilla.personal.works.redhabitat20.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import eder.padilla.personal.works.redhabitat20.Activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.Modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;
import io.realm.Realm;

/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_Encuesta_Pregunta_Nueve extends Fragment implements View.OnClickListener {
    Button btnTerminarEncuesta;
    EditText etComentarioFinal;
    Realm realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encuesta_pregunta_nueve, container, false);
        objectInitialization(view);

        btnTerminarEncuesta.setOnClickListener(this);
        System.out.println("Repuesta nueve es " + ((MainActivity) getActivity()).encuesta.getComentariofinal() + "compruebo uno " + ((MainActivity) getActivity()).encuesta.isCubrerequerimientos());
        return view;
    }

    public void createEncuestaRespondida(Encuesta encuesta) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(encuesta);
        realm.commitTransaction();
    }
    public static Fg_Encuesta_Pregunta_Nueve newInstance() {

        Fg_Encuesta_Pregunta_Nueve nueve_de_nueve = new Fg_Encuesta_Pregunta_Nueve();
        return nueve_de_nueve;
    }
    public void objectInitialization(View view) {
        realm = Realm.getInstance(getActivity().getApplicationContext());
        etComentarioFinal = (EditText) view.findViewById(R.id.comentariofinal);
        btnTerminarEncuesta = (Button) view.findViewById(R.id.final1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.final1:
                ((MainActivity) getActivity()).encuesta.setComentariofinal(etComentarioFinal.getText().toString());
                createEncuestaRespondida(((MainActivity) getActivity()).encuesta);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(0);
                Encuesta a = ((MainActivity)getActivity()).encuesta;
                System.out.println("El objeto es " + Util.toStringEncuesta(a));


                break;
        }
    }
}
