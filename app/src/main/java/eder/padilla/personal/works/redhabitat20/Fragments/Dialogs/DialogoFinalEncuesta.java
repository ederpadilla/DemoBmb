package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;

/**
 * Created by Eder on 01/05/2016.
 */
public class DialogoFinalEncuesta extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflamos nuestra vista */
        View view = inflater.inflate(R.layout.dialogo_final_registro_encuesta, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }







}
