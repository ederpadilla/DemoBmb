package eder.padilla.personal.works.redhabitat20.activitys;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import de.hdodenhof.circleimageview.CircleImageView;
import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.util.ConnectionDetector;

/**
 * Created by Eder on 12/04/2016.
 */
public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,OnDateSelectedListener, OnMonthChangedListener, DatePickerDialog.OnDateSetListener {
    View linlay,linlaya;
    LinearLayout cuadroDeCitas,
            domingo,lldomingo,
            lunes,lllunes,
            martes,llmartes,
            miercoles,llmiercoles,
            jueves,lljueves,
            viernes,llviernes,
            sabado,llsabado;

    MaterialCalendarView materialCalendarView;
    TextView tv_Fechaindice;
    MaterialSpinner spinner;
    TextView tv_primernumero_semana,
             tv_segundonumero_semana,
             tv_tercernumero_semana,
             tv_cuartonumero_semana,
             tv_quintonumero_semana,
             tv_sextonumero_semana,
             tv_septimonumero_semana;
    CircleImageView foto;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;





    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        objectInitialization();
        hideSystemUI();
        setListeners();
        adapterForCalendar();
        Calendar c= Calendar.getInstance();
        dayOfTheWeek(c);
        checkInternetConection();

        materialCalendarView.getShowOtherDates();
        tv_Fechaindice.setText(getSelectedDatesString());




    }
    /** Initializte all our objects . */

    private void objectInitialization() {
        cuadroDeCitas = (LinearLayout) findViewById(R.id.visita_cancelada_xml);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        tv_Fechaindice=(TextView) findViewById(R.id.fecha_indice);
        domingo =(LinearLayout)findViewById(R.id.linearlayaoutuno);
        lldomingo=(LinearLayout)findViewById(R.id.linearlayoutdomingo);
        lunes=(LinearLayout)findViewById(R.id.linearlayaoutdos);
        lllunes=(LinearLayout)findViewById(R.id.linearlayoutlunes);
        martes=(LinearLayout)findViewById(R.id.linearlayaouttres);
        llmartes=(LinearLayout)findViewById(R.id.linearlayoutmartes);
        miercoles=(LinearLayout)findViewById(R.id.linearlayaoutcuatro);
        llmiercoles=(LinearLayout)findViewById(R.id.linearlayoutmiercoles);
        jueves=(LinearLayout)findViewById(R.id.linearlayaoutcinco);
        lljueves=(LinearLayout)findViewById(R.id.linearlayoutjueves);
        viernes=(LinearLayout)findViewById(R.id.linearlayaoutseis);
        llviernes=(LinearLayout)findViewById(R.id.linearlayoutviernes);
        sabado=(LinearLayout)findViewById(R.id.linearlayaoutsiete);
        llsabado=(LinearLayout)findViewById(R.id.linearlayoutsabado);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        tv_primernumero_semana=(TextView)findViewById(R.id.numeroPrimerDiaDeLaSemana);
        tv_segundonumero_semana=(TextView)findViewById(R.id.numeroSegundoDiaDeLaSemana);
        tv_tercernumero_semana=(TextView)findViewById(R.id.numeroTercerDiaDelaSemana);
        tv_cuartonumero_semana=(TextView)findViewById(R.id.numeroCuartoDiaDeLaSemana);
        tv_quintonumero_semana=(TextView)findViewById(R.id.numeroQuintoDiaDeLaSemana);
        tv_sextonumero_semana=(TextView)findViewById(R.id.numeroSextoDiaDeLaSemana);
        tv_septimonumero_semana =(TextView)findViewById(R.id.numeroSeptimoDiaDeLaSemana);
        foto=(CircleImageView) findViewById(R.id.mnactv_fotodeasesor);
        cd = new ConnectionDetector(getApplicationContext());
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
    private void checkInternetConection(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        // get Internet status
        isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        if (isInternetPresent) {
            // Internet Connection is Present
            // make HTTP requests
            Toast toaste = Toast.makeText(context,"Existe conexión a internet", duration);
            toaste.show();
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            Toast toaste = Toast.makeText(context,"No existe conexion a internet", duration);
            toaste.show();
        }
    }

    private void setListeners() {

        cuadroDeCitas.setOnClickListener(this);
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        domingo.setOnClickListener(this);
        lldomingo.setOnClickListener(this);
        lunes.setOnClickListener(this);
        lllunes.setOnClickListener(this);
        martes.setOnClickListener(this);
        llmartes.setOnClickListener(this);
        miercoles.setOnClickListener(this);
        llmiercoles.setOnClickListener(this);
        jueves.setOnClickListener(this);
        lljueves.setOnClickListener(this);
        viernes.setOnClickListener(this);
        llviernes.setOnClickListener(this);
        sabado.setOnClickListener(this);
        llsabado.setOnClickListener(this);
        foto.setOnClickListener(this);
        spinner.setItems("Nombre del asesor","Finalizar cuestionario", "Cerrar sesión");
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
        if(linlaya!=null){
            linlaya.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if(linlay!=null){
            linlay.setBackgroundColor(getResources().getColor(R.color.white));
        }
        switch (v.getId()) {
            case R.id.visita_cancelada_xml:
                showEditDialog();
                break;
            case R.id.mnactv_fotodeasesor:
                Intent myIntent = new Intent(CalendarActivity.this, Resultados.class);
                CalendarActivity.this.startActivity(myIntent);
                break;
            case R.id.linearlayoutdomingo:
                setBackgroundSunday();
                  break;
            case R.id.linearlayaoutuno:
                setBackgroundSunday();
                break;

            case R.id.linearlayoutlunes:
                setBackgroundMonday();
                break;
            case R.id.linearlayaoutdos:
                setBackgroundMonday();
                break;
            case R.id.linearlayoutmartes:
               setBackgroundTuesday();
                break;
            case R.id.linearlayaouttres:
                setBackgroundTuesday();
                break;
            case R.id.linearlayoutmiercoles:
                setBackgroundWendesnay();
                break;
            case R.id.linearlayaoutcuatro:
                setBackgroundWendesnay();
                break;
            case R.id.linearlayoutjueves:
                setBackgroundThursday();
                break;
            case R.id.linearlayaoutcinco:
                setBackgroundThursday();
                break;
            case R.id.linearlayoutviernes:
                setBackgroundFriday();
                break;
            case R.id.linearlayaoutseis:
                setBackgroundFriday();
                break;
            case R.id.linearlayoutsabado:
                setBackgroundSaturday();
                break;
            case R.id.linearlayaoutsiete:
                setBackgroundSaturday();
                break;




        }

    }
    private void inflarVisitasProgramadas() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View laViewInflada = inflater.inflate(R.layout.visita_programada, null);
    }
    private void inflarVisitasNoRealizadas() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View laViewInflada = inflater.inflate(R.layout.visita_no_realizada, null);
    }
    private void inflarVisitasCanceladas() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View laViewInflada = inflater.inflate(R.layout.visita_cancelada, null);
    }
    private void inflarVisitasFinalizadas() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View laViewInflada = inflater.inflate(R.layout.visita_finalizada, null);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        tv_Fechaindice.setText(getSelectedDatesString());

        Calendar cal =  Calendar.getInstance();
        cal.setTime(date.getDate());

        System.out.println("Day of month :"+cal.get(Calendar.DAY_OF_MONTH));
        dayOfTheWeek(cal);
    }
    public void dayOfTheWeek(Calendar cal){
        if(linlay!=null){
            linlay.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if(linlaya!=null){
            linlaya.setBackgroundColor(getResources().getColor(R.color.white));
        }
        switch(cal.get(Calendar.DAY_OF_WEEK)){
        //    Calendar today=Calendar.getInstance();
            case 1:
                setBackgroundSunday();
                tv_Fechaindice.setText("Dom, "+getSelectedDatesString());
                selectSunday(cal);


                break;
            case 2:
                setBackgroundMonday();
                tv_Fechaindice.setText("Lun, "+getSelectedDatesString());
                selectMonday(cal);



                break;
            case 3:
                setBackgroundTuesday();
                tv_Fechaindice.setText("Mar, "+getSelectedDatesString());
                selectTuesday(cal);

                break;
            case 4:
                setBackgroundWendesnay();
                tv_Fechaindice.setText("Mier, "+getSelectedDatesString());
                selectWednesday(cal);

                break;
            case 5:
                setBackgroundThursday();
                tv_Fechaindice.setText("Jue, "+getSelectedDatesString());
                selectThursday(cal);

                break;
            case 6:
                setBackgroundFriday();
                tv_Fechaindice.setText("Vie, "+getSelectedDatesString());
                selectFriday(cal);


                break;
            case 7:
                setBackgroundSaturday();
                tv_Fechaindice.setText("Sab, "+getSelectedDatesString());
                selectSaturday(cal);


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
    private void selectSunday(Calendar cal){
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
    }
    private void selectMonday(Calendar cal){
        cal.add(Calendar.DATE,-1);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
    }
    private void selectTuesday(Calendar cal){

        cal.add(Calendar.DATE,-2);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
    }
    private void selectWednesday(Calendar cal){
        cal.add(Calendar.DATE,-3);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
    }
    private void selectThursday(Calendar cal){
        cal.add(Calendar.DATE,-4);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
    }
    private void selectFriday(Calendar cal){
        cal.add(Calendar.DATE,-5);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");

    }
    private void selectSaturday(Calendar cal){
        cal.add(Calendar.DATE,-6);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        cal.add(Calendar.DATE,1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH)+"");

    }
    private void setBackgroundSunday(){
        linlay=domingo;
        linlaya=lldomingo;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundMonday(){
        linlay=lunes;
        linlaya=lllunes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundTuesday(){
        linlay=martes;
        linlaya=llmartes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundWendesnay(){
        linlay=miercoles;
        linlaya=llmiercoles;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundThursday(){
        linlay=jueves;
        linlaya=lljueves;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundFriday(){
        linlay=viernes;
        linlaya=llviernes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundSaturday(){
        linlay=sabado;
        linlaya=llsabado;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));

    }

}
