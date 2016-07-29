package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;

/**
 * Created by Eder on 28/07/2016.
 */
public class Fg_Vp_Pregunta_Cuatro extends Fragment implements View.OnClickListener {
    private ImageButton btnBuenaInformacion;
    private ImageButton btnInformacionRegular;
    private ImageButton btnMalaInformacion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fg_vp_pregunta_cuatro,container,false);
        objectInitialization(view);
        setListener();

        return view;
    }
    public void objectInitialization(View view) {
        btnBuenaInformacion =(ImageButton) view.findViewById(R.id.buenacuatro);
        btnMalaInformacion =(ImageButton) view.findViewById(R.id.malacuatro);
        btnInformacionRegular =(ImageButton) view.findViewById(R.id.regularcuatro);
    }
    public void setListener(){
        btnBuenaInformacion.setOnClickListener(this);
        btnMalaInformacion.setOnClickListener(this);
        btnInformacionRegular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buenacuatro:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Buena");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
            case R.id.regularcuatro:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Regular");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
            case R.id.malacuatro:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Mala");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
        }
    }
}
