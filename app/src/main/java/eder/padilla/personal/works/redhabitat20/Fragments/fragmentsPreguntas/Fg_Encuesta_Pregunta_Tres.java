package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;


/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_Encuesta_Pregunta_Tres extends Fragment implements View.OnClickListener {
    private Button btnAceptar;
    private Button btnNegar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_encuesta_pregunta_tres,container,false);
        objectInitialization(view);
        setListeners();
        System.out.println("Repuesta tres es " + ((MainActivity) getActivity()).encuesta.isDentrodelpresupuesto());

        return view;
    }
    public void objectInitialization(View view){
        btnAceptar =(Button)view.findViewById(R.id.aceptar);
        btnNegar =(Button)view.findViewById(R.id.negar);
    }
    public void setListeners(){
        btnAceptar.setOnClickListener(this);
        btnNegar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aceptar:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setDentrodelpresupuesto(true);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(3);
                break;
            case R.id.negar:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setDentrodelpresupuesto(false);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(3);
                break;
        }
    }
}
