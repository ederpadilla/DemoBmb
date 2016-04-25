package eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import eder.padilla.personal.works.redhabitat20.activitys.CalendarActivity;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.Firma_Electronica;
import eder.padilla.personal.works.redhabitat20.util.Util;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;
import io.realm.Realm;

/**
 * Created by Eder on 15/03/2016.
 */
public class Fg_Encuesta_Pregunta_Nueve extends Fragment implements View.OnClickListener {
    private Button btnTerminarEncuesta;
    private EditText etComentarioFinal;
    private TextView firma_digital;
    private View decorView;
    Realm realm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encuesta_pregunta_nueve, container, false);
        objectInitialization(view);
        setListeneres();
        hideSystemUI();

        System.out.println("Repuesta nueve es " + ((MainActivity) getActivity()).encuesta.getComentarioFinal() + "compruebo uno " + ((MainActivity) getActivity()).encuesta.isCubreRequerimientos());
        return view;
    }

    public void createEncuestaRespondida(Encuesta encuesta) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(encuesta);
        realm.commitTransaction();
    }
    public static Fg_Encuesta_Pregunta_Nueve newInstance() {

        Fg_Encuesta_Pregunta_Nueve nueve_de_nueve = new Fg_Encuesta_Pregunta_Nueve();
        return nueve_de_nueve;
    }
    public void objectInitialization(View view) {
        realm = Realm.getInstance(getActivity().getApplicationContext());
        etComentarioFinal = (EditText) view.findViewById(R.id.comentariofinalNueve);
        btnTerminarEncuesta = (Button) view.findViewById(R.id.final1);
        firma_digital=(TextView)view.findViewById(R.id.firmadigital);

    }
    public void setListeneres(){
        firma_digital.setOnClickListener(this);
        btnTerminarEncuesta.setOnClickListener(this);
    }
    public void signatureRequest(){
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        Firma_Electronica editNameDialog = new Firma_Electronica();
        editNameDialog.show(fm, "fragment_firma_cliente");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.final1:
                  /*Casteamos nuestro objeto y asignamos valor al campo del modelo y llamamos a
                    * la proxima pagina del viewpager*/
                ((MainActivity) getActivity()).encuesta.setComentarioFinal(etComentarioFinal.getText().toString());
                createEncuestaRespondida(((MainActivity) getActivity()).encuesta);
                ((MainActivity) getActivity()).viewpager.setCurrentItem(0);
                Encuesta a = ((MainActivity)getActivity()).encuesta;
                System.out.println("El objeto es " + Util.toStringEncuesta(a));
                Intent myIntent = new Intent(getActivity(), CalendarActivity.class);
                getActivity().startActivity(myIntent);

                break;
            case R.id.firmadigital:
                signatureRequest();
                break;
        }
    }
    private void hideSystemUI() {
        decorView = getActivity().getWindow().getDecorView();
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
