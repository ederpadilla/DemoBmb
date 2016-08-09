package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.interfaces.InterfazPeticiones;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Prueba;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import eder.padilla.personal.works.redhabitat20.util.ServiceGenerator;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflamos nuestra vista */
        View view = inflater.inflate(R.layout.dialogo_gracias_visita_regustrada, container);
        objectInitialization(view);
        setListeners();
        return view;
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
                realm.beginTransaction();
                ((MainActivity) getActivity()).visita.setTipo(Constants.VISITA_TIPO_FINALIZADA);
                realm.copyToRealmOrUpdate(((MainActivity) getActivity()).visita);
                realm.commitTransaction();
                enviarEncuesta();
                Intent returnIntent = new Intent();
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

    List<Encuesta> realmEncuesta;

    public void enviarEncuesta() {
        InterfazPeticiones interfazPeticiones = ServiceGenerator.createService(InterfazPeticiones.class);


        final Call<ResponseBody> call = interfazPeticiones.mandarEncuestas(((MainActivity) getActivity()).token,new RealmList<Encuesta>());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> encuestas, Response<ResponseBody> response) {

                int statusCode = response.code();


                JSONObject jsonObject = new JSONObject();


                //Encuesta user = response.body();
                //Log.i("User ","Response body"+user.toString());
                //realm.beginTransaction();
                //realm.copyToRealmOrUpdate(response.body());
                //realm.commitTransaction();
//                System.out.println("El jonson es: "+response.body().toString());
                Log.e("login", "entre a onresponse");
                switch (statusCode) {
                    case 200:

                        break;
                    case 400:
                        break;
                    case 401:
                        break;
                    case 403:
                        break;
                    case 404:
                        break;
                    case 500:
                        break;

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("login", "entre a onfailure");
                int duration = Toast.LENGTH_SHORT;
            }
        });
    }

}
