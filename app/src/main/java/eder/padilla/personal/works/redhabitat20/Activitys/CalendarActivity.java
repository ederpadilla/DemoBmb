package eder.padilla.personal.works.redhabitat20.activitys;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.Calendar;


import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.adapters.AdaptadorVisitas;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Visita;
import eder.padilla.personal.works.redhabitat20.util.ConnectionDetector;
import eder.padilla.personal.works.redhabitat20.util.DividerItemDecoration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eder on 12/04/2016.
 */
public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, OnMonthChangedListener,
        DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener {
    private View linlay, linlaya;
    private LinearLayout
            domingo, lldomingo,
            lunes, lllunes,
            martes, llmartes,
            miercoles, llmiercoles,
            jueves, lljueves,
            viernes, llviernes,
            sabado, llsabado;
    private String nombreAsesor;
    private MaterialCalendarView materialCalendarView;
    private TextView tv_Fechaindice;
    private MaterialSpinner spinner;
    private TextView tv_primernumero_semana,
            tv_segundonumero_semana,
            tv_tercernumero_semana,
            tv_cuartonumero_semana,
            tv_quintonumero_semana,
            tv_sextonumero_semana,
            tv_septimonumero_semana;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    private RecyclerView recViewSunday, recViewMonday,
            recViewTuesday, recViewWednesday,
            recViewThursday, recViewFriday,
            recViewSaturday;
    private ArrayList<Visita> sundayData;
    private ArrayList<Visita> mondayData;
    private ArrayList<Visita> tuesdayData;
    private ArrayList<Visita> wednesdayData;
    private ArrayList<Visita> thursdayData;
    private ArrayList<Visita> fridayData;
    private ArrayList<Visita> saturdayData;
    private AdaptadorVisitas sundayAdapter;
    private AdaptadorVisitas mondayAdapter;
    private AdaptadorVisitas tuesdayAdapter;
    private AdaptadorVisitas wednesdayAdapter;
    private AdaptadorVisitas thursdayAdapter;
    private AdaptadorVisitas fridayAdapter;
    private AdaptadorVisitas saturdayAdapter;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        objectInitialization();
        setListeners();
        adapterForCalendar();
        Calendar c = Calendar.getInstance();
        dayOfTheWeek(c);
        checkInternetConection();
        tv_Fechaindice.setText(getSelectedDatesString());
        fillInAllArrayList();
        setListenersToAdapters();
        addVisits();

    }

    /**
     * Initializte all our objects .
     **/

    private void objectInitialization() {
        /**Calendario . */
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        /** TextView. */
        tv_Fechaindice = (TextView) findViewById(R.id.fecha_indice);
        tv_primernumero_semana = (TextView) findViewById(R.id.numeroPrimerDiaDeLaSemana);
        tv_segundonumero_semana = (TextView) findViewById(R.id.numeroSegundoDiaDeLaSemana);
        tv_tercernumero_semana = (TextView) findViewById(R.id.numeroTercerDiaDelaSemana);
        tv_cuartonumero_semana = (TextView) findViewById(R.id.numeroCuartoDiaDeLaSemana);
        tv_quintonumero_semana = (TextView) findViewById(R.id.numeroQuintoDiaDeLaSemana);
        tv_sextonumero_semana = (TextView) findViewById(R.id.numeroSextoDiaDeLaSemana);
        tv_septimonumero_semana = (TextView) findViewById(R.id.numeroSeptimoDiaDeLaSemana);
        /**Liear Layouts . */
        domingo = (LinearLayout) findViewById(R.id.linearlayaoutuno);
        lldomingo = (LinearLayout) findViewById(R.id.linearlayoutdomingo);
        lunes = (LinearLayout) findViewById(R.id.linearlayaoutdos);
        lllunes = (LinearLayout) findViewById(R.id.linearlayoutlunes);
        martes = (LinearLayout) findViewById(R.id.linearlayaouttres);
        llmartes = (LinearLayout) findViewById(R.id.linearlayoutmartes);
        miercoles = (LinearLayout) findViewById(R.id.linearlayaoutcuatro);
        llmiercoles = (LinearLayout) findViewById(R.id.linearlayoutmiercoles);
        jueves = (LinearLayout) findViewById(R.id.linearlayaoutcinco);
        lljueves = (LinearLayout) findViewById(R.id.linearlayoutjueves);
        viernes = (LinearLayout) findViewById(R.id.linearlayaoutseis);
        llviernes = (LinearLayout) findViewById(R.id.linearlayoutviernes);
        sabado = (LinearLayout) findViewById(R.id.linearlayaoutsiete);
        llsabado = (LinearLayout) findViewById(R.id.linearlayoutsabado);
        /** Spinner. */
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        /** Recicler Views. */
        recViewSunday = (RecyclerView) findViewById(R.id.RecViewDomingo);
        recViewSunday.setHasFixedSize(true);

        recViewMonday = (RecyclerView) findViewById(R.id.RecViewLunes);
        recViewMonday.setHasFixedSize(true);
        recViewMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("myLog", "clcik monday");
            }
        });
        recViewTuesday = (RecyclerView) findViewById(R.id.RecViewMartes);
        recViewTuesday.setHasFixedSize(true);
        recViewWednesday = (RecyclerView) findViewById(R.id.RecViewMiercoles);
        recViewWednesday.setHasFixedSize(true);
        recViewThursday = (RecyclerView) findViewById(R.id.RecViewJueves);
        recViewThursday.setHasFixedSize(true);
        recViewFriday = (RecyclerView) findViewById(R.id.RecViewViernes);
        recViewFriday.setHasFixedSize(true);
        recViewSaturday = (RecyclerView) findViewById(R.id.RecViewSabado);
        recViewSaturday.setHasFixedSize(true);
        /** OInternet Conection detector. */
        cd = new ConnectionDetector(getApplicationContext());
        nombreAsesor = "Asesor";
        /** ArrayLists. */
        sundayData = new ArrayList<Visita>();
        mondayData = new ArrayList<Visita>();
        tuesdayData = new ArrayList<Visita>();
        wednesdayData = new ArrayList<Visita>();
        thursdayData = new ArrayList<Visita>();
        fridayData = new ArrayList<Visita>();
        saturdayData = new ArrayList<Visita>();
    }

    /**
     * Fill All the arrlists with null parameters.
     */
    private void fillInAllArrayList() {
        for (int i = 0; i < 24; i++) {
            sundayData.add(new Visita("", "", "", 0, null));
            mondayData.add(new Visita("", "", "", 0, null));
            tuesdayData.add(new Visita("", "", "", 0, null));
            wednesdayData.add(new Visita("", "", "", 0, null));
            thursdayData.add(new Visita("", "", "", 0, null));
            fridayData.add(new Visita("", "", "", 0, null));
            saturdayData.add(new Visita("", "", "", 0, null));
        }
        setAdapters();
    }

    /**
     * Asign the behavior to the recyclerviews.
     */
    private void setAdapters() {
        sundayAdapter = new AdaptadorVisitas(sundayData, this.getApplicationContext());
        recViewSunday.setAdapter(sundayAdapter);
        recViewSunday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewSunday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewSunday.setItemAnimator(new DefaultItemAnimator());

        mondayAdapter = new AdaptadorVisitas(mondayData, this.getApplicationContext());
        recViewMonday.setAdapter(mondayAdapter);
        recViewMonday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewMonday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewMonday.setItemAnimator(new DefaultItemAnimator());
        tuesdayAdapter = new AdaptadorVisitas(tuesdayData, this.getApplicationContext());
        recViewTuesday.setAdapter(tuesdayAdapter);
        recViewTuesday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewTuesday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewTuesday.setItemAnimator(new DefaultItemAnimator());
        wednesdayAdapter = new AdaptadorVisitas(wednesdayData, this.getApplicationContext());
        recViewWednesday.setAdapter(wednesdayAdapter);
        recViewWednesday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewWednesday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewWednesday.setItemAnimator(new DefaultItemAnimator());
        thursdayAdapter = new AdaptadorVisitas(thursdayData, this.getApplicationContext());
        recViewThursday.setAdapter(thursdayAdapter);
        recViewThursday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewThursday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewThursday.setItemAnimator(new DefaultItemAnimator());
        fridayAdapter = new AdaptadorVisitas(fridayData, this.getApplicationContext());
        recViewFriday.setAdapter(fridayAdapter);
        recViewFriday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewFriday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewFriday.setItemAnimator(new DefaultItemAnimator());
        saturdayAdapter = new AdaptadorVisitas(saturdayData, this.getApplicationContext());
        recViewSaturday.setAdapter(saturdayAdapter);
        recViewSaturday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recViewSaturday.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recViewSaturday.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * We check the position we are clicking on .
     */
    private void setListenersToAdapters() {
        sundayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SundayRecView", "Pulsado el elemento " + recViewSunday.getChildAdapterPosition(v));
                Visita visita = sundayData.get(recViewSunday.getChildAdapterPosition(v));
                check_State(visita);

            }
        });
        mondayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MondayRecView", "Pulsado el elemento " + recViewMonday.getChildAdapterPosition(v));
                Visita visita = mondayData.get(recViewMonday.getChildAdapterPosition(v));
                check_State(visita);
            }
        });
        tuesdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TuesdayRecView", "Pulsado el elemento " + recViewTuesday.getChildAdapterPosition(v));
                Visita visita = tuesdayData.get(recViewTuesday.getChildAdapterPosition(v));
                check_State(visita);
            }
        });
        wednesdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("WednesdayRecView", "Pulsado el elemento " + recViewWednesday.getChildAdapterPosition(v));
                Visita visita = wednesdayData.get(recViewWednesday.getChildAdapterPosition(v));
                check_State(visita);
            }
        });
        thursdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ThursdayRecView", "Pulsado el elemento " + recViewThursday.getChildAdapterPosition(v));
                Visita visita = thursdayData.get(recViewThursday.getChildAdapterPosition(v));
                check_State(visita);

            }
        });
        fridayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FridayRecView", "Pulsado el elemento " + recViewFriday.getChildAdapterPosition(v));
                Visita visita = fridayData.get(recViewFriday.getChildAdapterPosition(v));
                check_State(visita);
            }
        });
        saturdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SaturdayRecView", "Pulsado el elemento " + recViewSaturday.getChildAdapterPosition(v));
                Visita visita = saturdayData.get(recViewSaturday.getChildAdapterPosition(v));
                check_State(visita);
            }
        });
    }

    private void check_State(Visita visita) {
        if (visita.getTipo().equals("programada")) {
            showEditDialog();
            Log.e("Checando el estado", "entro al programada");
        }
        Log.e("Checando el estado", "entro al otro");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * Select the adapter for manage the calendar.
     */
    public void adapterForCalendar() {
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        CalendarDay today = CalendarDay.today();
        materialCalendarView.setDateSelected(today, true);
    }


    private void checkInternetConection() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;


        /**get Internet status .**/
        isInternetPresent = cd.isConnectingToInternet();


        /** check for Internet status.**/
        if (isInternetPresent) {

            /**Internet Connection is Present
             // make HTTP requests .**/
            Toast toaste = Toast.makeText(context, "Existe conexión a internet", duration);
            toaste.show();
        } else {

            /**Internet connection is not present
             // Ask user to connect to Internet .**/
            Toast toaste = Toast.makeText(context, "No existe conexion a internet", duration);
            toaste.show();
        }
    }

    private void setListeners() {

        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        domingo.setOnClickListener(this);
        lunes.setOnClickListener(this);
        martes.setOnClickListener(this);
        miercoles.setOnClickListener(this);
        jueves.setOnClickListener(this);
        viernes.setOnClickListener(this);
        sabado.setOnClickListener(this);
        spinnerAdapter();

    }

    private void spinnerAdapter() {
        spinner.setItems(nombreAsesor, getResources().getString(R.string.cerrarsesion));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                if (item.equals(getResources().getString(R.string.cerrarsesion))) {
                    SharedPreferences sp = getSharedPreferences("Login", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();
                    deleteRealmBBDD();
                    Intent intent = new Intent(CalendarActivity.this,
                            Splash.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showEditDialog() {
        DiaologoPreguntaRealizarEncuesta editNameDialog = new DiaologoPreguntaRealizarEncuesta();
        editNameDialog.show(getFragmentManager(), "diaologo_preguntar_encuesta");
    }

    private void getDatesforAsesor() {
        Visita visita = new Visita();
        for (int i = 0; i < 24; i++) {
            if (i % 2 != 0)
                System.out.println("no es par");
            else
                System.out.println("es par");
        }
    }

    @Override
    public void onClick(View v) {
        if (linlaya != null) {
            linlaya.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if (linlay != null) {
            linlay.setBackgroundColor(getResources().getColor(R.color.white));
        }
        /** Asign backgorung color to our dates. */
        switch (v.getId()) {
            case R.id.linearlayaoutuno:
                tv_Fechaindice.setText("Dom, " + getSelectedDatesString());
                Log.e("click a un algo","no se que pedotl");
                setBackgroundSunday();
                break;

            case R.id.linearlayaoutdos:
                tv_Fechaindice.setText("Lun, " + getSelectedDatesString());
                setBackgroundMonday();
                break;
            case R.id.linearlayaouttres:
                tv_Fechaindice.setText("Mar, " + getSelectedDatesString());
                setBackgroundTuesday();
                break;

            case R.id.linearlayaoutcuatro:
                tv_Fechaindice.setText("Mier, " + getSelectedDatesString());
                setBackgroundWendesnay();
                break;
            case R.id.linearlayaoutcinco:
                tv_Fechaindice.setText("Jue, " + getSelectedDatesString());
                setBackgroundThursday();
                break;
            case R.id.linearlayaoutseis:
                tv_Fechaindice.setText("Vie, " + getSelectedDatesString());
                setBackgroundFriday();
                break;
            case R.id.linearlayaoutsiete:
                tv_Fechaindice.setText("Sab, " + getSelectedDatesString());
                setBackgroundSaturday();
                break;
        }
    }

    private void addVisits() {
        Visita domingo = new Visita("Jose Palacios", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 0);
        Visita lunes = new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 1);
        Visita martes = new Visita("Luis Nuño", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 2);
        Visita miercoles = new Visita("Juan Ruvalcaba", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 3);
        Visita jueves = new Visita("Ken el cabron", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 4);
        Visita viernes = new Visita("Eder Padilla", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 5);
        Visita sabado = new Visita("Lucio Sanchez", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 6);
        for (int i = 0; i < 24; i++) {
            sundayData.set(i, domingo);
            sundayAdapter.notifyDataSetChanged();
            mondayData.set(i, lunes);
            mondayAdapter.notifyDataSetChanged();
            tuesdayData.set(i, martes);
            tuesdayAdapter.notifyDataSetChanged();
            wednesdayData.set(i, miercoles);
            wednesdayAdapter.notifyDataSetChanged();
            thursdayData.set(i, jueves);
            thursdayAdapter.notifyDataSetChanged();
            fridayData.set(i, viernes);
            fridayAdapter.notifyDataSetChanged();
            saturdayData.set(i, sabado);
            saturdayAdapter.notifyDataSetChanged();
        }

    }

    /**
     * Check the date we elect.
     */
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        tv_Fechaindice.setText(getSelectedDatesString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getDate());
        Log.e("date selected", "" + materialCalendarView.getSelectedDate());
        dayOfTheWeek(cal);
    }


    public void dayOfTheWeek(Calendar cal) {
        if (linlay != null) {
            linlay.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if (linlaya != null) {
            linlaya.setBackgroundColor(getResources().getColor(R.color.white));
        }


        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                Log.e("caso uno","no se que pedotl jajaja");
                setBackgroundSunday();
                tv_Fechaindice.setText("Dom, " + getSelectedDatesString());
                selectSunday(cal);


                break;
            case 2:
                setBackgroundMonday();
                tv_Fechaindice.setText("Lun, " + getSelectedDatesString());
                selectMonday(cal);


                break;
            case 3:
                setBackgroundTuesday();
                tv_Fechaindice.setText("Mar, " + getSelectedDatesString());
                selectTuesday(cal);
                break;
            case 4:
                setBackgroundWendesnay();
                tv_Fechaindice.setText("Mier, " + getSelectedDatesString());
                selectWednesday(cal);
                break;
            case 5:
                setBackgroundThursday();
                tv_Fechaindice.setText("Jue, " + getSelectedDatesString());
                selectThursday(cal);
                break;
            case 6:
                setBackgroundFriday();
                tv_Fechaindice.setText("Vie, " + getSelectedDatesString());
                selectFriday(cal);

                break;
            case 7:
                setBackgroundSaturday();
                tv_Fechaindice.setText("Sab, " + getSelectedDatesString());
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


    /**
     * Set the dates in our weeks if we select sunday.
     */
    private void selectSunday(Calendar cal) {
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * Set the dates in our weeks if we select monday.
     */
    private void selectMonday(Calendar cal) {
        cal.add(Calendar.DATE, -1);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * Set the dates in our weeks if we select tuesday.
     */
    private void selectTuesday(Calendar cal) {

        cal.add(Calendar.DATE, -2);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * Set the dates in our weeks if we select wednesday.
     */
    private void selectWednesday(Calendar cal) {
        cal.add(Calendar.DATE, -3);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * Set the dates in our weeks if we select thursday.
     */
    private void selectThursday(Calendar cal) {
        cal.add(Calendar.DATE, -4);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * Set the dates in our weeks if we select friday.
     */
    private void selectFriday(Calendar cal) {
        cal.add(Calendar.DATE, -5);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");

    }

    /**
     * Set the dates in our weeks if we select saturday.
     */
    private void selectSaturday(Calendar cal) {
        cal.add(Calendar.DATE, -6);
        tv_primernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(cal.get(Calendar.DAY_OF_MONTH) + "");

    }

    private void setBackgroundSunday() {
        linlay = domingo;
        linlaya = lldomingo;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundMonday() {
        linlay = lunes;
        linlaya = lllunes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundTuesday() {
        linlay = martes;
        linlaya = llmartes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundWendesnay() {
        linlay = miercoles;
        linlaya = llmiercoles;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundThursday() {
        linlay = jueves;
        linlaya = lljueves;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundFriday() {
        linlay = viernes;
        linlaya = llviernes;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }

    private void setBackgroundSaturday() {
        linlay = sabado;
        linlaya = llsabado;
        linlay.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linlaya.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));

    }

    private void deleteRealmBBDD() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        realm.clear(Encuesta.class);
        realm.commitTransaction();

    }


}
