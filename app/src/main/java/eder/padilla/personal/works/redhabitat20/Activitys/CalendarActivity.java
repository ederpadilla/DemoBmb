package eder.padilla.personal.works.redhabitat20.activitys;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


import java.util.Calendar;
import java.util.Date;


import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;

/**
 * Created by Eder on 12/04/2016.
 */
public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout act_calendar_uno, act_calendar_dos, act_calendar_tres, act_calendar_cuatro, act_calendar_cinco;
    LinearLayout cuadroDeCitas;

    MaterialCalendarView materialCalendarView;

    Calendar hoy = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calendar);
        objectInitialization();
        hideSystemUI();
        setListeners();
        selectionModeforCalendar();
        materialCalendarView.getShowOtherDates();


    }


    private void objectInitialization() {
        cuadroDeCitas = (LinearLayout) findViewById(R.id.cuadro_de_citas_xml);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);


        //act_calendar_dos,act_calendar_tres,act_calendar_cuatro,act_calendar_cinco

    }

    public void selectionModeforCalendar() {
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        CalendarDay today= CalendarDay.today();
        materialCalendarView.setSelectedDate(today);

    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
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

    private void setListeners() {
        cuadroDeCitas.setOnClickListener(this);
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DiaologoPreguntaRealizarEncuesta editNameDialog = new DiaologoPreguntaRealizarEncuesta();
        editNameDialog.show(getFragmentManager(), "diaologo_preguntar_encuesta");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cuadro_de_citas_xml:
                showEditDialog();

                break;

        }

    }


}
