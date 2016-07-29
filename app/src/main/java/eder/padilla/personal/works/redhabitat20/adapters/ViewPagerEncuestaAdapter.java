package eder.padilla.personal.works.redhabitat20.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Vp_Pregunta_Cuatro;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Vp_Pregunta_Tres;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Ocho;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Siete;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Cinco;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Seis;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Dos;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_ViewPager_Uno;

/**
 * Created by Eder on 04/04/2016.
 */
/* Clase que se encarga del comportamiento del View Pager */
public class ViewPagerEncuestaAdapter extends FragmentPagerAdapter {

    public ViewPagerEncuestaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return new Fg_ViewPager_Uno();
            case 1: return new Fg_ViewPager_Dos();
            case 2: return new Fg_Vp_Pregunta_Tres();
            case 3: return new Fg_Vp_Pregunta_Cuatro();
            case 4: return new Fg_ViewPager_Cinco();
            case 5: return new Fg_ViewPager_Seis();
            case 6: return new Fg_ViewPager_Siete();
            case 7: return new Fg_ViewPager_Ocho();

            default: return Fg_ViewPager_Ocho.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 8;
    }
}