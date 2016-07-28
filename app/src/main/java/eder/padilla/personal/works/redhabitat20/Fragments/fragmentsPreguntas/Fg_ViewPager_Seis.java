package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import de.hdodenhof.circleimageview.CircleImageView;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;


/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_ViewPager_Seis extends Fragment implements View.OnClickListener {
    private ImageButton btnBuenaAtencion;
    private ImageButton btnAtencionRegular;
    private ImageButton btnMalaAtencion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_encuesta_pregunta_siete,container,false);
        objectInitialization(view);
        setListener();
        System.out.println("Repuesta siete es " + ((MainActivity) getActivity()).encuesta.getAtencion());

        return view;
    }
    public void objectInitialization(View view) {
        btnBuenaAtencion =(ImageButton) view.findViewById(R.id.buena);
        btnMalaAtencion =(ImageButton) view.findViewById(R.id.mala);
        btnAtencionRegular =(ImageButton) view.findViewById(R.id.regular);
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
                ((MainActivity) getActivity()).encuesta.setAtencion("Buena");
                ((MainActivity) getActivity()).viewpager.setCurrentItem(6);
                break;
            case R.id.regular:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setAtencion("Regular");
               ((MainActivity) getActivity()).viewpager.setCurrentItem(6);
                break;
            case R.id.mala:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setAtencion("Mala");
                 ((MainActivity) getActivity()).viewpager.setCurrentItem(6);
                break;
        }
    }
}
