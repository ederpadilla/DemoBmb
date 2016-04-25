package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DialogoComentarioPropietario;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Eder on 14/03/2016.
 */
public class Fg_Encuesta_Pregunta_Uno extends Fragment implements View.OnClickListener {

    private Button btnAceptar;
    private Button btnNegar;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_ecuesta_pregunta_uno,container,false);
        objectInitialization(view);
        setListeners();
        showEditDialog();
        System.out.println("Repuesta uno es " + ((MainActivity) getActivity()).encuesta.isCubreRequerimientos());


        return view;
    }
    public void objectInitialization(View view){
        btnAceptar =(Button)view.findViewById(R.id.sidelauno);
        btnNegar =(Button)view.findViewById(R.id.nodelauno);

    }
    public void setListeners(){
        btnAceptar.setOnClickListener(this);
        btnNegar.setOnClickListener(this);
    }
    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        DialogoComentarioPropietario editNameDialog = new DialogoComentarioPropietario();
        editNameDialog.show(getActivity().getFragmentManager(),"diaologo_comentario_propietario_encuesta");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sidelauno:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/

                ((MainActivity) getActivity()).encuesta.setCubreRequerimientos(true);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(1);
                break;

            case R.id.nodelauno:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/

                ((MainActivity) getActivity()).encuesta.setCubreRequerimientos(false);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(1);
                break;

        }
    }
}
