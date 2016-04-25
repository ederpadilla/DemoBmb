package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;


/**
 * Created by Eder on 14/03/2016.
 */
public class Fg_Encuesta_Pregunta_Dos extends Fragment implements View.OnClickListener {
    private Button btnSiguiente;
    private EditText et_respuestadelPorqueCubreoNo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_encuesta_pregunta_dos, container, false);
        objectInitialization(view);
        setListeners();
        System.out.println("Repuesta dos es " + ((MainActivity) getActivity()).encuesta.getElpPorQueCubreoNo());
        return view;
    }
    public void objectInitialization(View view){
        et_respuestadelPorqueCubreoNo =(EditText)view.findViewById(R.id.comentario);
        btnSiguiente =(Button)view.findViewById(R.id.siguiente);

    }
    public void setListeners(){
        btnSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.siguiente:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setElpPorQueCubreoNo(et_respuestadelPorqueCubreoNo.getText().toString());
                ((MainActivity) getActivity()).viewpager.setCurrentItem(2);
                break;
        }
    }
}
