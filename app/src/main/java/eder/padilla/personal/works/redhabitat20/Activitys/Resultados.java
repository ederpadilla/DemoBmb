package eder.padilla.personal.works.redhabitat20.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.modelos.Asesor;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.util.Util;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Resultados extends AppCompatActivity  {
    LinearLayout tabla;
    Realm realm;
    private RealmResults<Encuesta> allEncuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        objectInit();
        llenarTabla();
    }

    private void llenarTabla() {
        allEncuestas = realm.where(Encuesta.class).findAll();
        RealmResults<Encuesta> results=allEncuestas;
        for (Encuesta encuesta : results) {
            TextView datos, fecha, status;
            View inflatedView = View.inflate(getBaseContext(), R.layout.reglon, null);
            tabla.addView(inflatedView, 0);

            datos = (TextView) inflatedView.findViewById(R.id.tv_datos);
            fecha = (TextView) inflatedView.findViewById(R.id.fecha);
            status = (TextView) inflatedView.findViewById(R.id.status);

            datos.setText(Util.toStringEncuesta(encuesta));
            fecha.setText(encuesta.getFecha());
            status.setText("not yet");
        }

    }

    private void objectInit() {
        tabla=(LinearLayout)findViewById(R.id.informacion);
        realm = Realm.getInstance(getApplicationContext());

//        this.allEncuestas.addChangeListener(this);
    }



}
