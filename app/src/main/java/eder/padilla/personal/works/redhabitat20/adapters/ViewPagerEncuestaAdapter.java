package eder.padilla.personal.works.redhabitat20.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Cinco;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Cuatro;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Dos;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Nueve;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Ocho;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Seis;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Siete;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Tres;
import eder.padilla.personal.works.redhabitat20.fragments.fragmentsPreguntas.Fg_Encuesta_Pregunta_Uno;

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

            case 0: return new Fg_Encuesta_Pregunta_Uno();
            case 1: return new Fg_Encuesta_Pregunta_Dos();
            case 2: return new Fg_Encuesta_Pregunta_Tres();
            case 3: return  new Fg_Encuesta_Pregunta_Cuatro();
            case 4: return  new Fg_Encuesta_Pregunta_Cinco();
            case 5: return  new Fg_Encuesta_Pregunta_Seis();
            case 6: return  new Fg_Encuesta_Pregunta_Siete();
            case 7: return  new Fg_Encuesta_Pregunta_Ocho();
            case 8: return  new Fg_Encuesta_Pregunta_Nueve();

            default: return Fg_Encuesta_Pregunta_Nueve.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 9;
    }
}