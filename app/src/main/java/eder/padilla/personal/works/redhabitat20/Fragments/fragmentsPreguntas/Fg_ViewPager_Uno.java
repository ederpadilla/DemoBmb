package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;

/**
 * Created by Eder on 14/03/2016.
 */
public class Fg_ViewPager_Uno extends Fragment implements View.OnClickListener {


    EditText et_PorqueLoCalificas;
    Button bt_siguiente;
    private RadioGroup radio_g;
    private RadioButton radio_b;
    private int calif;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_ecuesta_pregunta_uno,container,false);
        objectInitialization(view);
        setListeners();
        return view;
    }
    public void objectInitialization(View view){
        et_PorqueLoCalificas=(EditText)view.findViewById(R.id.et_porquelocalificaasi);
        bt_siguiente=(Button)view.findViewById(R.id.bt_siguiente_preguntauno);
        radio_g = (RadioGroup)view.findViewById(R.id.radio_grouppreguntauno);
    }

    public void setListeners(){
        bt_siguiente.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_siguiente_preguntauno:
                int selected_id = radio_g.getCheckedRadioButtonId();
                if (selected_id==-1){
                    Log.e("mylog","aqui entra"+selected_id);
                    ((MainActivity) getActivity()).encuesta.setCalificacionInmueble(0);
                }else{
                radio_b = (RadioButton)radio_g.findViewById(selected_id);
                calif= Integer.parseInt(radio_b.getText().toString());
                Log.i("","calif "+calif);
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setCalificacionInmueble(calif);}
                ((MainActivity) getActivity()).encuesta.setPorQueLoCalificasAsi(et_PorqueLoCalificas.getText().toString());
                Log.e("","et_porque_calificas "+et_PorqueLoCalificas.getText().toString());
                ((MainActivity) getActivity()).viewpager.setCurrentItem(1);
                break;
        }
    }
}