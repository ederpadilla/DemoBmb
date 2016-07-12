package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Eder on 10/04/2016.
 */
public class DiaologoPreguntaRealizarEncuesta extends DialogFragment implements View.OnClickListener {
    private Button mbt_RealizarEncuesta;
    private Button mbt_NoRealizarEncuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflamos nuestra vista */
        View view = inflater.inflate(R.layout.dialogo_pregunta_realizar_encuesta, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        objectInitialization(view);
        setListeners();
        return view;
    }

    /** Referenciamos nuestros objetos */
    private void objectInitialization(View view) {
        mbt_RealizarEncuesta = (Button) view.findViewById(R.id.bt_dialogo_aceptar_encuesta);
        mbt_NoRealizarEncuesta =(Button) view.findViewById(R.id.bt__dialogo_cancelar_encuesta);


    }

    private void setListeners(){
        mbt_NoRealizarEncuesta.setOnClickListener(this);
        mbt_RealizarEncuesta.setOnClickListener(this);

    }


    /** Asignamos funcionalidades a los botones */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_dialogo_aceptar_encuesta:
                dismiss();
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivityForResult(myIntent,1);
                break;
            case R.id.bt__dialogo_cancelar_encuesta:
                dismiss();
                break;
        }
    }


}
