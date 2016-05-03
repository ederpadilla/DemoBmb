package eder.padilla.personal.works.redhabitat20.activitys;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;

/**
 * Created by Eder on 12/04/2016.
 */
public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,OnDateSelectedListener, OnMonthChangedListener, DatePickerDialog.OnDateSetListener {
    View linlay;
    LinearLayout cuadroDeCitas,
            domingo,
    lunes,
    martes,
    miercoles,
    jueves,
    viernes,
    sabado;

    MaterialCalendarView materialCalendarView;
    TextView tv_Fechaindice;
    MaterialSpinner spinner;
    Calendar c = Calendar.getInstance();








    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calendar);
        objectInitialization();
        hideSystemUI();
        setListeners();
        adapterForCalendar();
        dayOfTheWeek(c);
        materialCalendarView.getShowOtherDates();
        tv_Fechaindice.setText(getSelectedDatesString());



    }
    /** Initializte all our objects . */

    private void objectInitialization() {
        cuadroDeCitas = (LinearLayout) findViewById(R.id.cuadro_de_citas_xml);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        tv_Fechaindice=(TextView) findViewById(R.id.fecha_indice);
        domingo =(LinearLayout)findViewById(R.id.linearlayaoutuno);
        lunes=(LinearLayout)findViewById(R.id.linearlayaoutdos);
        martes=(LinearLayout)findViewById(R.id.linearlayaouttres);
        miercoles=(LinearLayout)findViewById(R.id.linearlayaoutcuatro);
        jueves=(LinearLayout)findViewById(R.id.linearlayaoutcinco);
        viernes=(LinearLayout)findViewById(R.id.linearlayaoutseis);
        sabado=(LinearLayout)findViewById(R.id.linearlayaoutsiete);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
    }


    public void adapterForCalendar() {
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
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
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        spinner.setItems("","Finalizar cuestionario", "Cerrar sesión");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
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

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        tv_Fechaindice.setText(getSelectedDatesString());

        System.out.println("El dia es :"+date.getDay());
        System.out.println("Current date :"+widget.getCurrentDate());
        System.out.println("Date to string es"+date.toString());
        DatePickerDialog dp= new DatePickerDialog(this,this,2016,1,1);
        Calendar cal =  Calendar.getInstance();
        cal.setTime(date.getDate());

        dayOfTheWeek(cal);

    }
    public void dayOfTheWeek(Calendar cal){
        if(linlay!=null){
            linlay.setBackgroundColor(getResources().getColor(R.color.white));
        }
        System.out.print("Dia del mes es :"+ cal.get(Calendar.DAY_OF_MONTH));

        switch(cal.get(Calendar.DAY_OF_WEEK)){
            case 1:
                linlay=domingo;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Dom, "+getSelectedDatesString());
                break;
            case 2:
                linlay=lunes;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Lun, "+getSelectedDatesString());
                break;
            case 3:
                linlay=martes;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Mar, "+getSelectedDatesString());
                break;
            case 4:
                linlay=miercoles;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Mier, "+getSelectedDatesString());
                break;
            case 5:
                linlay=jueves;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Jue, "+getSelectedDatesString());
                break;
            case 6:
                linlay=viernes;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Vie, "+getSelectedDatesString());
                break;
            case 7:
                linlay=sabado;
                linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
                tv_Fechaindice.setText("Sab, "+getSelectedDatesString());
                break;
        }

    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
      
    }

    private String getSelectedDatesString() {
        CalendarDay date = materialCalendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }


}
