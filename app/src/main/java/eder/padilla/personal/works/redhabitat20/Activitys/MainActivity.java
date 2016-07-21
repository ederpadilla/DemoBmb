package eder.padilla.personal.works.redhabitat20.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;


import eder.padilla.personal.works.redhabitat20.adapters.ViewPagerEncuestaAdapter;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DialogoComentarioPropietario;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DialogoFinalEncuesta;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DialogoFinalizarAntesCuestionario;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    /* Declaracion de objetos con los que se trbajara */
    public Encuesta encuesta;
    public ViewPager viewpager;
    private TextView mTvIndice;
    private MaterialSpinner mSpinner;
    private RealmConfiguration realmConfig;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectInitialization();
        spinnerAdapter();

    }
    /* Referenciamos nuestros objetos*/
   public void objectInitialization() {
       realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
       realm=Realm.getInstance(realmConfig);
       encuesta = new Encuesta();
       viewpager = (ViewPager) findViewById(R.id.viewPager);
       viewpager.setAdapter(new ViewPagerEncuestaAdapter(getSupportFragmentManager()));
       viewpager.addOnPageChangeListener(this);
       mTvIndice =(TextView) findViewById(R.id.main_indice);
       mSpinner = (MaterialSpinner) findViewById(R.id.spinnerCuestionario);
       mTvIndice.setText(1+"");
    }
    private void spinnerAdapter() {
        SharedPreferences userDetails = getSharedPreferences(Constants.LLAVE_LOGIN,0);
        String Uname = userDetails.getString(Constants.NOMBRE_ASESOR, "");
        String mNombreAsesor=getResources().getString(R.string.asesor);
        final String mFinalizarCuestionario=getResources().getString(R.string.finalizar_cuestionario);
        final String cerrarSesion=getResources().getString(R.string.cerrarsesion);
        mSpinner.setItems(Uname,cerrarSesion,mFinalizarCuestionario);
        mSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                if (item.equals(cerrarSesion)) {
                    SharedPreferences sp = getSharedPreferences(Constants.LLAVE_LOGIN, 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();
                    deleteRealmBBDD();
                    Intent intent = new Intent(MainActivity.this,
                            Splash.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }else if(item.equals(mFinalizarCuestionario)){
                    showEditDialog();
                }
            }
        });
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        /* decimos en que posicion se encuentra nuestro indice*/
        mTvIndice.setText(position+1+"");
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
       private void deleteRealmBBDD(){
        realm.beginTransaction();
        realm.delete(Encuesta.class);
        realm.commitTransaction();
    }
    private void showEditDialog() {
        DialogoFinalizarAntesCuestionario editNameDialog = new DialogoFinalizarAntesCuestionario();
        editNameDialog.show(getFragmentManager(), "diaologo_preguntar_encuesta");
    }
}
