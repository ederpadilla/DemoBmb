package eder.padilla.personal.works.redhabitat20.Activitys;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import eder.padilla.personal.works.redhabitat20.Adapters.MyPagerAdapter;
import eder.padilla.personal.works.redhabitat20.Modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.R;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    public Encuesta encuesta;
    public ViewPager viewpager;
    public static TextView tv_indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectInitialization();
    }
   public void objectInitialization() {
        encuesta = new Encuesta();
        viewpager = (ViewPager) findViewById(R.id.viewPager);
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(this);
        tv_indice=(TextView) findViewById(R.id.main_indice);
        tv_indice.setText(1+"");
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        tv_indice.setText(position+1+"");
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
