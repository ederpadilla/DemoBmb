package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.Activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import eder.padilla.personal.works.redhabitat20.util.Util;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Eder on 28/07/2016.
 */
public class Dialogo_Comentario_Del_Asesor extends DialogFragment implements View.OnClickListener {
    private Button mbt_realizar_comentario_propietario;
    private Button mbt_no_realizar_comentario;
    private EditText met_Comentario_del_Asesor;
    private RealmResults<Encuesta> allEncuestas;
    private Realm realm;
    RealmConfiguration realmConfiguration;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_gracias_visita_regustrada, null, false);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        // Add action buttons
        objectInitialization(view);
        setListeners();
        return builder.create();
    }


    /* Metodo para referenciar nuestros objetos */
    private void objectInitialization(View view) {
        mbt_realizar_comentario_propietario = (Button) view.findViewById(R.id.bt_guardar_comentario_asesor);
        mbt_no_realizar_comentario = (Button) view.findViewById(R.id.bt_dialogo_cancelar_comentario_del_asesor);
        met_Comentario_del_Asesor = (EditText) view.findViewById(R.id.et_comentario_del_asesor);
        realmConfiguration = new RealmConfiguration.Builder(getActivity()).build();
        realm = Realm.getInstance(realmConfiguration);
        allEncuestas = realm.where(Encuesta.class).findAll();


    }

    /* Asignamos listeners a los botones */
    public void setListeners() {
        mbt_no_realizar_comentario.setOnClickListener(this);
        mbt_realizar_comentario_propietario.setOnClickListener(this);
    }

    /* Asignamos funcionalidades al hacer click en botones */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.bt_guardar_comentario_asesor:
                ((MainActivity) getActivity()).encuesta.setIdd(allEncuestas.size() + 1);
                ((MainActivity) getActivity()).encuesta.setComentarioDelAsesor(met_Comentario_del_Asesor.getText().toString());
                createEncuestaRespondida(((MainActivity) getActivity()).encuesta);
                Encuesta encuesta_contestada = ((MainActivity) getActivity()).encuesta;
                System.out.println("El objeto es " + encuesta_contestada.toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.RESULT_OF_END_QUIZ, "finalizada");
                Log.i("ponemos en el extra", "" + returnIntent.getExtras());
                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                getActivity().finish();

                dismiss();
                break;
            case R.id.bt_dialogo_cancelar_comentario_del_asesor:
                dismiss();
                break;
        }
    }

    public void createEncuestaRespondida(Encuesta encuesta) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(encuesta);
        realm.commitTransaction();
    }

}
