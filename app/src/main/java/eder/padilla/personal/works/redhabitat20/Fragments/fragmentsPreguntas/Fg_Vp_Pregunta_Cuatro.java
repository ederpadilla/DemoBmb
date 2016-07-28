package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private ImageButton btnBuenaAtencion;
    private ImageButton btnAtencionRegular;
    private ImageButton btnMalaAtencion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fg_vp_pregunta_cuatro,container,false);
        objectInitialization(view);
        setListener();

        return view;
    }
    public void objectInitialization(View view) {
        btnBuenaAtencion =(ImageButton) view.findViewById(R.id.buenacuatro);
        btnMalaAtencion =(ImageButton) view.findViewById(R.id.malacuatro);
        btnAtencionRegular =(ImageButton) view.findViewById(R.id.regularcuatro);
    }
    public void setListener(){
        btnBuenaAtencion.setOnClickListener(this);
        btnMalaAtencion.setOnClickListener(this);
        btnAtencionRegular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buena:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Buena");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
            case R.id.regular:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Regular");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
            case R.id.mala:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setInformacionQueRecibioSobreLaPropiedad("Mala");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(4);
                break;
        }
    }
}
