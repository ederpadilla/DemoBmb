package eder.padilla.personal.works.redhabitat20.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import eder.padilla.personal.works.redhabitat20.adapters.EncuestaAdapter;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    /* Declaracion de objetos con los que se trbajara */
    public Encuesta encuesta;
    public ViewPager viewpager;
    private TextView mTvIndice;

    View decorView;
    MaterialSpinner spinner;
    String nombreAsesor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSystemUI();
        objectInitialization();
    }
    /* Referenciamos nuestros objetos*/
   public void objectInitialization() {
        encuesta = new Encuesta();
        nombreAsesor="Eder";
        viewpager = (ViewPager) findViewById(R.id.viewPager);
        viewpager.setAdapter(new EncuestaAdapter(getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(this);
        mTvIndice =(TextView) findViewById(R.id.main_indice);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
       spinner.setItems(nombreAsesor, "Cerrar sesión");
       spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

           @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

               if(item.equals(getResources().getString(R.string.cerrarsesion))){
                   SharedPreferences sp=getSharedPreferences("Login", 0);
                   SharedPreferences.Editor Ed=sp.edit();
                   Ed.putString("Unm","" );
                   Ed.putString("Psw","");
                   Ed.commit();
                   Intent intent = new Intent(MainActivity.this,
                           Splash.class);intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                   startActivity(intent);
               }
           }
       });

        mTvIndice.setText(1+"");
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


    private void hideSystemUI() {
        decorView = getWindow().getDecorView();
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
