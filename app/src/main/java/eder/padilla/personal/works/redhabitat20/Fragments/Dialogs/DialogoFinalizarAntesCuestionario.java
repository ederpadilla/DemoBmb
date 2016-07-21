package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.CalendarActivity;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eder on 20/07/2016.
 */
public class DialogoFinalizarAntesCuestionario extends DialogFragment implements View.OnClickListener {
    private Button mbt_RealizarEncuesta;
    private Button mbt_NoRealizarEncuesta;
    private EditText met_PorqueFinalizoCuestionario;
    Realm realm;
    RealmConfiguration config;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflamos nuestra vista */
        View view = inflater.inflate(R.layout.dialogo_pregunta_porque_finaliza, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        objectInitialization(view);
        setListeners();
        return view;
    }

    /** Referenciamos nuestros objetos */
    private void objectInitialization(View view) {
        mbt_RealizarEncuesta = (Button) view.findViewById(R.id.bt_dialogo_cancelar_finalizar);
        mbt_NoRealizarEncuesta =(Button) view.findViewById(R.id.bt_guardar_finalizar);
        met_PorqueFinalizoCuestionario=(EditText)view.findViewById(R.id.porquefinalizoelcuestionario);
        config = new RealmConfiguration.Builder(getActivity().getApplicationContext()).build();
        realm=Realm.getInstance(config);
    }

    private void setListeners(){
        mbt_NoRealizarEncuesta.setOnClickListener(this);
        mbt_RealizarEncuesta.setOnClickListener(this);

    }


    /** Asignamos funcionalidades a los botones */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_guardar_finalizar:
                ((MainActivity) getActivity()).encuesta.setComentarioFinalizarAntes(met_PorqueFinalizoCuestionario.getText().toString());
                createEncuestaRespondida(((MainActivity) getActivity()).encuesta);
                Log.i("FinalizarEncuesta","seCrea "+((MainActivity) getActivity()).encuesta.toString());
                dismiss();
                Intent intent = new Intent(getActivity(),CalendarActivity.class);
                getActivity().finish();
                startActivity(intent);
                break;
            case R.id.bt_dialogo_cancelar_finalizar:
                dismiss();
                break;
        }
    }
    private void createEncuestaRespondida(Encuesta encuesta) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(encuesta);
        realm.commitTransaction();
    }


}
