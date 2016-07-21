package eder.padilla.personal.works.redhabitat20.activitys;

import android.app.Activity;
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

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.adapters.AdaptadorVisitas;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Visita;
import eder.padilla.personal.works.redhabitat20.util.ConnectionDetector;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import eder.padilla.personal.works.redhabitat20.util.DividerItemDecoration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eder on 12/04/2016.
 */
public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, OnMonthChangedListener,
        DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener {
    private View linearLayout, linearPrueba;
    private LinearLayout
            domingo, lldomingo,
            lunes, lllunes,
            martes, llmartes,
            miercoles, llmiercoles,
            jueves, lljueves,
            viernes, llviernes,
            sabado, llsabado;
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
    private String setFinalType;
    private Calendar cprueba;
    private Realm realm;
    private RealmConfiguration realmConfiguration;
    private int semanaDelAño;
    private int semanaDinamica;
    private DotProgressBar dotProgressBar;
    private String semanaDomingo,semanaLunes,semanaMartes,
                   semanaMiercoles,semanaJueves,semanaViernes,
                   semanaSabado;


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
        onChangeWeekListener();
    }
    @Override
    public void onResume() {
        super.onResume();
        addVisits();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String res = data.getStringExtra(Constants.RESULT_OF_END_QUIZ);
                Log.e("Resultado en string", "" + res);
                setFinalType = res;
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }//onActivityResult

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
        /** ArrayLists. */
        sundayData = new ArrayList<Visita>();
        mondayData = new ArrayList<Visita>();
        tuesdayData = new ArrayList<Visita>();
        wednesdayData = new ArrayList<Visita>();
        thursdayData = new ArrayList<Visita>();
        fridayData = new ArrayList<Visita>();
        saturdayData = new ArrayList<Visita>();
        /**Dot progress bar*/
        dotProgressBar=(DotProgressBar) findViewById(R.id.dot_progress_barcalendar);
        dotProgressBar.setStartColor(getResources().getColor(R.color.peach));
        dotProgressBar.setEndColor(getResources().getColor(R.color.salmon_orange));
        dotProgressBar.setVisibility(View.GONE);
        /**Realm*/
        realmConfiguration = new RealmConfiguration.Builder(getApplicationContext()).build();
        realm = Realm.getInstance(realmConfiguration);
    }
    /**Listeners*/
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
    @Override
    public void onClick(View v) {
        if (linearPrueba != null) {
            linearPrueba.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
        /** Asign backgorung color to our dates. */
        switch (v.getId()) {
            case R.id.linearlayaoutuno:
                tv_Fechaindice.setText("Dom, " + getSelectedDatesString());
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
    /**
     * Fill All the arrlists with null parameters.
     */
    private void fillInAllArrayList() {
        for (int i = 0; i < 24; i++) {
            sundayData.add(new Visita("", "", ""));
            mondayData.add(new Visita("", "", ""));
            tuesdayData.add(new Visita("", "", ""));
            wednesdayData.add(new Visita("", "", ""));
            thursdayData.add(new Visita("", "", ""));
            fridayData.add(new Visita("", "", ""));
            saturdayData.add(new Visita("", "", ""));
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
                Visita visita = sundayData.get(recViewSunday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);

            }
        });
        mondayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = mondayData.get(recViewMonday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);
            }
        });
        tuesdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = tuesdayData.get(recViewTuesday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);
            }
        });
        wednesdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = wednesdayData.get(recViewWednesday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);
            }
        });
        thursdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = thursdayData.get(recViewThursday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);

            }
        });
        fridayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = fridayData.get(recViewFriday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);
            }
        });
        saturdayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visita visita = saturdayData.get(recViewSaturday.getChildAdapterPosition(v));
                check_State(visita);
                //visita.setTipo(setFinalType);
            }
        });
    }
    /**Internet Conection . */
    private void checkInternetConection() {
        Context context = getApplicationContext();



        /**get Internet status .**/
        isInternetPresent = cd.isConnectingToInternet();


        /** check for Internet status.**/
        if (isInternetPresent) {

            /**Internet Connection is Present
             // make HTTP requests .**/
            //Toast toaste = Toast.makeText(context, "Existe conexión a internet", duration);
            //toaste.show();
        } else {

            /**Internet connection is not present
             // Ask user to connect to Internet .**/
            //Toast toaste = Toast.makeText(context, "No existe conexion a internet", duration);
            //toaste.show();
        }
    }
    /**Spinner. */
    private void spinnerAdapter() {
        /**SharedPreferences userDetails = getSharedPreferences(Constants.LLAVE_LOGIN,0);
         String Uname = userDetails.getString(Constants.NOMBRE_ASESOR, "");
         String mNombreAsesor=getResources().getString(R.string.asesor);
         final String mFinalizarCuestionario=getResources().getString(R.string.finalizar_cuestionario);
         final String cerrarSesion=getResources().getString(R.string.cerrarsesion);
         mSpinner.setItems(Uname*/
        SharedPreferences userDetails = getSharedPreferences(Constants.LLAVE_LOGIN,0);
        String Uname = userDetails.getString(Constants.NOMBRE_ASESOR, "");
        String nombreAsesor=getResources().getString(R.string.asesor);
        String cerrarSesion=getResources().getString(R.string.cerrarsesion);
        spinner.setItems(Uname, cerrarSesion);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                if (item.equals(getResources().getString(R.string.cerrarsesion))) {
                    SharedPreferences sp = getSharedPreferences(Constants.LLAVE_LOGIN, 0);
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

    private void deleteRealmBBDD() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        realm.delete(Encuesta.class);
        realm.commitTransaction();
    }
    /**
     *
     *
     * Material Calendar. */
    public void adapterForCalendar() {
        /**
         * Select the adapter for manage the calendar.
         */
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        CalendarDay today = CalendarDay.today();
        materialCalendarView.setDateSelected(today, true);
    }
    /**
     * Check the date we elect.
     */
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        tv_Fechaindice.setText(getSelectedDatesString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getDate());
        date.getDay();
        dayOfTheWeek(cal);
        semanaDelAño=cal.get(Calendar.WEEK_OF_YEAR);
        onChangeWeekListener();
    }
    private  void onChangeWeekListener(){
        if (semanaDinamica != semanaDelAño){
            semanaDinamica= semanaDelAño;
            dotProgressBar.setVisibility(View.VISIBLE);
            getFullweek();
        }
        else {
            dotProgressBar.setVisibility(View.GONE);
        }
        checkWeekForTheVisit();
    }
    private void checkWeekForTheVisit(){
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String dateInString = "15/7/2016";
        try {
            java.util.Date datetest = formatter.parse(dateInString);
            Calendar test = Calendar.getInstance();
            test.setTime(datetest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * Return the date in String.
     */
    private String getSelectedDatesString() {
        CalendarDay date = materialCalendarView.getSelectedDate();
        cprueba = date.getCalendar();
        materialCalendarView.getSelectedDate().getDay();
        if (date == null) {
            return "No Selection";
        }
        return new SimpleDateFormat(Constants.DATE_FORMAT).format(date.getDate()).replace("-","/");
    }
    private void check_State(Visita visita) {
        Log.e("visita", "visita" + visita.toString());
        if (visita.getTipo().equals("programada")) {
            showEditDialog();
            Log.e("Checando el estado", "entro al programada");
        }
        Log.e("Checando el estado", "entro al otro");
    }

    private void getFullweek(){
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        String dateInString = getSelectedDatesString();
        Calendar test = Calendar.getInstance();
        try {
            java.util.Date datetest = formatter.parse(dateInString);
            test.setTime(datetest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (test.get(Calendar.DAY_OF_WEEK)){
            case 1:
                Log.i("domingo",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("domingo","sabado"+provisionalFormat(test));
                break;
            case 2:
                test.add(Calendar.DATE, -1);
                Log.i("lunes","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("lunes","sabado"+provisionalFormat(test));
                break;
            case 3:
                test.add(Calendar.DATE, -2);
                Log.i("martes","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("martes","sabado"+provisionalFormat(test));
                break;
            case 4:
                test.add(Calendar.DATE, -3);
                Log.i("miercoles","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("miercoles","sabado"+provisionalFormat(test));
                break;
            case 5:
                test.add(Calendar.DATE, -4);
                Log.i("jueves","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("jueves","sabado"+provisionalFormat(test));
                break;
            case 6:
                test.add(Calendar.DATE, -5);
                Log.i("viernes","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes",""+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("viernes","sabado"+provisionalFormat(test));
                break;
            case 7:
                test.add(Calendar.DATE, -6);
                Log.i("sabado","domingo"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado","lunes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado","martes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado","miercoles"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado","jueves"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                Log.i("sabado",""+provisionalFormat(test));
                break;
        }
    }
    private String provisionalFormat(Calendar calendar){
        return calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
    }


    /**
     *
     *  Agenda.
     *
     */
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
    private String provitionalTVDate(Calendar calendar){
        return calendar.get(Calendar.DAY_OF_MONTH) + "";
    }
    private void selectMonday(Calendar cal) {
        cal.add(Calendar.DATE, -1);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));
    }

    /**
     * Set the dates in our weeks if we select tuesday.
     */
    private void selectTuesday(Calendar cal) {

        cal.add(Calendar.DATE, -2);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));
    }

    /**
     * Set the dates in our weeks if we select wednesday.
     */

    private void selectWednesday(Calendar cal) {
        cal.add(Calendar.DATE, -3);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));
    }

    /**
     * Set the dates in our weeks if we select thursday.
     */
    private void selectThursday(Calendar cal) {
        cal.add(Calendar.DATE, -4);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));
    }

    /**
     * Set the dates in our weeks if we select friday.
     */
    private void selectFriday(Calendar cal) {
        cal.add(Calendar.DATE, -5);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));

    }

    /**
     * Set the dates in our weeks if we select saturday.
     */
    private void selectSaturday(Calendar cal) {
        cal.add(Calendar.DATE, -6);
        tv_primernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_segundonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_tercernumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_cuartonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_quintonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_sextonumero_semana.setText(provitionalTVDate(cal));
        cal.add(Calendar.DATE, 1);
        tv_septimonumero_semana.setText(provitionalTVDate(cal));
    }
    private void multipleLinears(View linearLayout, View linearLayout1){
        linearLayout.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
        linearLayout1.setBackgroundColor(getResources().getColor(R.color.backgroundlinears));
    }
    private void setBackgroundSunday() {
        linearLayout = domingo;
        linearPrueba = lldomingo;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundMonday() {
        linearLayout = lunes;
        linearPrueba = lllunes;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundTuesday() {
        linearLayout = martes;
        linearPrueba = llmartes;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundWendesnay() {
        linearLayout = miercoles;
        linearPrueba = llmiercoles;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundThursday() {
        linearLayout = jueves;
        linearPrueba = lljueves;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundFriday() {
        linearLayout = viernes;
        linearPrueba = llviernes;
        multipleLinears(linearLayout, linearPrueba);
    }

    private void setBackgroundSaturday() {
        linearLayout = sabado;
        linearPrueba = llsabado;
        multipleLinears(linearLayout, linearPrueba);

    }
    private void showEditDialog() {
        DiaologoPreguntaRealizarEncuesta editNameDialog = new DiaologoPreguntaRealizarEncuesta();
        editNameDialog.show(getFragmentManager(), "diaologo_preguntar_encuesta");
    }
    public void dayOfTheWeek(Calendar cal) {
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if (linearPrueba != null) {
            linearPrueba.setBackgroundColor(getResources().getColor(R.color.white));
        }
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
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

    private void createVisit(Visita visita) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(visita);
        realm.commitTransaction();
    }

    private void addVisits() {
        Visita domingo = new Visita("Jose Palacios", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 0);
        Visita lunes = new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 1);
        Visita martes = new Visita("Luis Nuño", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 2);
        Visita miercoles = new Visita("Juan Ruvalcaba", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 3);
        Visita jueves = new Visita("Ken el cabron", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 4);
        Visita viernes = new Visita("Eder Padilla", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "finalizada", 5);
        Visita sabado = new Visita("Lucio Sanchez", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", "programada", 6);
        sundayData.set(0, domingo);
        sundayAdapter.notifyDataSetChanged();
        mondayData.set(1, lunes);
        mondayAdapter.notifyDataSetChanged();
        tuesdayData.set(2, martes);
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayData.set(3, miercoles);
        wednesdayAdapter.notifyDataSetChanged();
        thursdayData.set(4, jueves);
        thursdayAdapter.notifyDataSetChanged();
        fridayData.set(5, viernes);
        fridayAdapter.notifyDataSetChanged();
        saturdayData.set(6, sabado);
        saturdayAdapter.notifyDataSetChanged();
    }

    /**
     *
     *
     *
     *
     * */
    private String getTheWeekDay(int diaDeLaSemana) {
        int dia = cprueba.get(Calendar.DAY_OF_WEEK);
        String day = "";
        switch (dia) {
            case 1:
                day = "domingo";
                break;
            case 2:
                day = "lunes";
                break;
            case 3:
                day = "martes";
                break;
            case 4:
                day = "miercoles";
                break;
            case 5:
                day = "jueves";
                break;
            case 6:
                day = "viernes";
                break;
            case 7:
                day = "sabado";
                break;
        }
        return day;
    }

    private int dayOfMonth() {
        int dia = cprueba.get(Calendar.DAY_OF_MONTH);
        return dia;
    }

    private String mothAtIt() {
        int mesprueba = cprueba.get(Calendar.MONTH);
       String mes="";
        switch (mesprueba) {
            case 0:
                mes = "ene.";
                break;
            case 1:
                mes = "feb.";
                break;
            case 2:
                mes = "mar.";
                break;
            case 3:
                mes = "abr.";
                break;
            case 4:
                mes = "may.";
                break;
            case 5:
                mes = "jun.";
                break;
            case 6:
                mes = "jul.";
                break;
            case 7:
                mes = "ago.";
                break;
            case 8:
                mes = "sep.";
                break;
            case 9:
                mes = "oct.";
                break;
            case 10:
                mes = "nov.";
                break;
            case 11:
                mes = "dic.";
                break;
        }
        return mes;
    }

    private int yearAtIt() {
        int year = cprueba.get(Calendar.YEAR);
        return year;
    }




    @Override
    public String[] fileList() {
        return super.fileList();
    }




    private void fillSunday() {
        Visita prueba = new Visita("Eder","Toluca","programada","14/7/2016",0);
        Log.e("fechaDelaprueba" ,""+prueba.getDateOfVisit());
        Log.e("fechaseleccionada" ,""+getSelectedDatesString());
        Log.e("visitaprueba" ,""+prueba.getDateOfVisit());
        if (getSelectedDatesString().equals(prueba.getDateOfVisit())){
            Log.e("Log prueba","estamos en el mismo canal");
            Calendar tes = cprueba;
            Log.i("Obtenemos","que sera en "+getTheWeekDay(tes.get(Calendar.DAY_OF_WEEK)));
        } else Log.e("Sorry","No estamos en el mismo canal");
    }

    private void fillMonday() {
    }

    private void fillTuesday() {
    }

    private void fillWednesday() {
    }

    private void fillThursday() {
    }

    private void fillFriday() {
    }

    private void fillSaturday() {
    }


}
