package eder.padilla.personal.works.redhabitat20.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.adapters.AdaptadorVisitas;
import eder.padilla.personal.works.redhabitat20.fragments.dialogs.DiaologoPreguntaRealizarEncuesta;
import eder.padilla.personal.works.redhabitat20.interfaces.InterfazPeticiones;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Visita;
import eder.padilla.personal.works.redhabitat20.util.ConnectionDetector;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import eder.padilla.personal.works.redhabitat20.util.DividerItemDecoration;
import eder.padilla.personal.works.redhabitat20.util.ServiceGenerator;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String semanaDomingo,semanaLunes,semanaMartes,
            semanaMiercoles,semanaJueves,semanaViernes,
            semanaSabado;
    Bundle bundle;
    SharedPreferences userDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        objectInitialization();
        setListeners();
        adapterForCalendar();
        Calendar c = Calendar.getInstance();
        semanaDelAño=c.get(Calendar.WEEK_OF_YEAR);
        semanaDinamica=semanaDelAño;
        dayOfTheWeek(c);
        fillInAllArrayList();
        /**checkInternetConection();*/
        setListenersToAdapters();
        getFullweek();
        addVisits();
        onChangeWeekListener();
        String token = userDetails.getString(Constants.TOKEN_FINAL, "");
        Log.i("calendaractivity"," token es "+token);




    }
    public void tokenOfsahred(){
        String token = userDetails.getString(Constants.TOKEN_FINAL, "");
        Log.i("calendaractivity"," token es "+token);
        InterfazPeticiones interfazPeticiones= ServiceGenerator.createService(InterfazPeticiones.class);
        interfazPeticiones.obtenerVisitasdelBack(getSelectedDatesString(),token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("responsebody",""+response.code());
                try {
                    Log.i("OnResponse ",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("entra a ","onFailiure");

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.e("entra a"," On Resume");
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
        saturdayAdapter.notifyDataSetChanged();
        //addVisits();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String res = data.getStringExtra(Constants.RESULT_OF_END_QUIZ);
                setFinalType = res;
                emptyAllArrayList();


                RealmResults<Visita> allVisits = realm.where(Visita.class).findAll();
                for (int i = 0; i <allVisits.size() ; i++) {
                    acomodarVisita(allVisits.get(i));
                }


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
    private void emptyAllArrayList() {
        Visita emptyVisit= new Visita("","","");
        for (int i = 0; i < 24; i++) {
            sundayData.set(i,emptyVisit);
            mondayData.set(i,emptyVisit);
            tuesdayData.set(i,emptyVisit);
            wednesdayData.set(i,emptyVisit);
            thursdayData.set(i,emptyVisit);
            fridayData.set(i,emptyVisit);
            saturdayData.set(i,emptyVisit);
        }
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
        saturdayAdapter.notifyDataSetChanged();
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

        userDetails = getSharedPreferences(Constants.LLAVE_LOGIN,0);
        String Uname = userDetails.getString(Constants.NOMBRE_ASESOR, "");
        String nombreAsesor=getResources().getString(R.string.asesor);
        String cerrarSesion=getResources().getString(R.string.cerrarsesion);
        spinner.setItems(Uname, cerrarSesion);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

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
        Log.e("ondateSelcted"," "+getSelectedDatesString());

    }
    private  void onChangeWeekListener(){
        if (semanaDinamica != semanaDelAño){
            semanaDinamica= semanaDelAño;
            emptyAllArrayList();
            getFullweek();
            addVisits();
            Log.e("myLog","semana diferente");
        }
        else {
            Log.e("myLog","misma semana");
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
        if (visita.getTipo().equals(Constants.VISITA_TIPO_PROGRAMADA)) {
            bundle = new Bundle();
            bundle.putInt(Constants.ID_VIEW_VISITA,visita.getIdd());
            Log.i("myLog","visita que mandamos"+visita.getIdd());
            showEditDialog();
            Log.e("Checando el estado", "entro al programada");
        }else{
        Log.e("Checando el estado", "entro al otro");}
    }


    private void getFullweek(){
        Calendar test = stringToCalendar(getSelectedDatesString());

        switch (test.get(Calendar.DAY_OF_WEEK)){
            case 1:
                semanaDomingo=provisionalFormat(test);
                Log.i("domingo",""+semanaDomingo);
                test.add(Calendar.DATE, 1);
                semanaLunes=provisionalFormat(test);
                Log.i("domingo","lunes "+semanaLunes);
                test.add(Calendar.DATE, 1);
                semanaMartes=provisionalFormat(test);
                Log.i("domingo","martes "+semanaMartes);
                test.add(Calendar.DATE, 1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("domingo","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE, 1);
                semanaJueves=provisionalFormat(test);
                Log.i("domingo","jueves "+semanaJueves);
                test.add(Calendar.DATE, 1);
                semanaViernes=provisionalFormat(test);
                Log.i("domingo","viernes"+provisionalFormat(test));
                test.add(Calendar.DATE, 1);
                semanaSabado=provisionalFormat(test);
                Log.i("domingo","sabado"+semanaSabado);
                break;
            case 2:
                test.add(Calendar.DATE,-1);
                semanaDomingo=provisionalFormat(test);
                Log.i("lunes","domingo "+semanaDomingo);
                test.add(Calendar.DATE,1);
                semanaLunes=provisionalFormat(test);
                Log.i("lunes",""+semanaLunes);
                test.add(Calendar.DATE,1);
                semanaMartes=provisionalFormat(test);
                Log.i("lunes","martes "+semanaMartes);
                test.add(Calendar.DATE,1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("lunes","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE,1);
                semanaJueves=provisionalFormat(test);
                Log.i("lunes","jueves "+semanaJueves);
                test.add(Calendar.DATE,1);
                semanaViernes=provisionalFormat(test);
                Log.i("lunes","viernes "+semanaViernes);
                test.add(Calendar.DATE,1);
                semanaSabado=provisionalFormat(test);
                Log.i("lunes","sabado "+semanaSabado);
                break;
            case 3:
                test.add(Calendar.DATE, -2);
                semanaDomingo=provisionalFormat(test);
                Log.i("martes","domingo "+semanaDomingo);
                test.add(Calendar.DATE, 1);
                semanaLunes=provisionalFormat(test);
                Log.i("martes","lunes "+semanaLunes);
                test.add(Calendar.DATE, 1);
                semanaMartes=provisionalFormat(test);
                Log.i("martes",""+semanaMartes);
                test.add(Calendar.DATE, 1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("martes","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE, 1);
                semanaJueves=provisionalFormat(test);
                Log.i("martes","jueves "+semanaJueves);
                test.add(Calendar.DATE, 1);
                semanaViernes=provisionalFormat(test);
                Log.i("martes","viernes "+semanaViernes);
                test.add(Calendar.DATE, 1);
                semanaSabado=provisionalFormat(test);
                Log.i("martes","sabado "+semanaSabado);
                break;
            case 4:
                test.add(Calendar.DATE,-3);
                semanaDomingo=provisionalFormat(test);
                Log.i("miercoles","domingo "+semanaDomingo);
                test.add(Calendar.DATE,1);
                semanaLunes=provisionalFormat(test);
                Log.i("miercoles","lunes "+semanaLunes);
                test.add(Calendar.DATE,1);
                semanaMartes=provisionalFormat(test);
                Log.i("miercoles","martes "+semanaMartes);
                test.add(Calendar.DATE,1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("miercoles",""+semanaMiercoles);
                test.add(Calendar.DATE,1);
                semanaJueves=provisionalFormat(test);
                Log.i("miercoles","jueves "+semanaJueves);
                test.add(Calendar.DATE,1);
                semanaViernes=provisionalFormat(test);
                Log.i("miercoles","viernes "+semanaViernes);
                test.add(Calendar.DATE,1);
                semanaSabado=provisionalFormat(test);
                Log.i("miercoles","sabado "+semanaSabado);
                break;
            case 5:
                test.add(Calendar.DATE, -4);
                semanaDomingo=provisionalFormat(test);
                Log.i("jueves","domingo "+semanaDomingo);
                test.add(Calendar.DATE, 1);
                semanaLunes=provisionalFormat(test);
                Log.i("jueves","lunes "+semanaLunes);
                test.add(Calendar.DATE, 1);
                semanaMartes=provisionalFormat(test);
                Log.i("jueves","martes "+semanaMartes);
                test.add(Calendar.DATE, 1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("jueves","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE, 1);
                semanaJueves=provisionalFormat(test);
                Log.i("jueves",""+semanaJueves);
                test.add(Calendar.DATE, 1);
                semanaViernes=provisionalFormat(test);
                Log.i("jueves","viernes "+semanaViernes);
                test.add(Calendar.DATE, 1);
                semanaSabado=provisionalFormat(test);
                Log.i("jueves","sabado "+semanaSabado);
                break;
            case 6:
                test.add(Calendar.DATE, -5);
                semanaDomingo=provisionalFormat(test);
                Log.i("viernes","domingo "+semanaDomingo);
                test.add(Calendar.DATE, 1);
                semanaLunes=provisionalFormat(test);
                Log.i("viernes","lunes "+semanaLunes);
                test.add(Calendar.DATE, 1);
                semanaMartes=provisionalFormat(test);
                Log.i("viernes","martes "+semanaMartes);
                test.add(Calendar.DATE, 1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("viernes","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE, 1);
                semanaJueves=provisionalFormat(test);
                Log.i("viernes","jueves "+semanaJueves);
                test.add(Calendar.DATE, 1);
                semanaViernes=provisionalFormat(test);
                Log.i("viernes",""+semanaViernes);
                test.add(Calendar.DATE, 1);
                semanaSabado=provisionalFormat(test);
                Log.i("viernes","sabado "+semanaSabado);
                break;
            case 7:
                test.add(Calendar.DATE, -6);
                semanaDomingo=provisionalFormat(test);
                Log.i("sabado","domingo "+semanaDomingo);
                test.add(Calendar.DATE, 1);
                semanaLunes=provisionalFormat(test);
                Log.i("sabado","lunes "+semanaLunes);
                test.add(Calendar.DATE, 1);
                semanaMartes=provisionalFormat(test);
                Log.i("sabado","martes "+semanaMartes);
                test.add(Calendar.DATE, 1);
                semanaMiercoles=provisionalFormat(test);
                Log.i("sabado","miercoles "+semanaMiercoles);
                test.add(Calendar.DATE, 1);
                semanaJueves=provisionalFormat(test);
                Log.i("sabado","jueves "+semanaJueves);
                test.add(Calendar.DATE, 1);
                semanaViernes=provisionalFormat(test);
                Log.i("sabado","viernes "+semanaViernes);
                test.add(Calendar.DATE, 1);
                semanaSabado=provisionalFormat(test);
                Log.i("sabado",""+semanaSabado);
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
        DiaologoPreguntaRealizarEncuesta diaologoPreguntaRealizarEncuesta = new DiaologoPreguntaRealizarEncuesta();
         diaologoPreguntaRealizarEncuesta.setArguments(bundle);
         diaologoPreguntaRealizarEncuesta.show(getFragmentManager(), "diaologo_preguntar_encuesta");
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
    private Calendar stringToCalendar(String fecha){
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        Calendar test = Calendar.getInstance();
        try {
            java.util.Date datetest = formatter.parse(fecha);
            test.setTime(datetest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return test;
    }
    private int horaAsignada (Visita visita){
        return visita.getHora();
    }


    private void addVisits() {
        RealmList<Visita> visitas= new RealmList<>();

        Visita domingo = new Visita("Jose Palacios", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_PROGRAMADA,"24/6/2016",0,1);
        Visita lunes2 = new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_FINALIZADA,"25/6/2016",3,2);
        Visita lunes = new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_FINALIZADA,"25/6/2016",1,3);
        Visita alguna = new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_PROGRAMADA,"19/6/2016",3,4);
        Visita lunes3= new Visita("Alejandra Rosalva", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México",Constants.VISITA_TIPO_FINALIZADA,"25/6/2016",5,5);
        Visita martes = new Visita("Luis Nuño", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_PROGRAMADA,"26/6/2016",5,6);
        Visita miercoles = new Visita("Juan Ruvalcaba", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_FINALIZADA,"27/6/2016",13,7);
        Visita jueves = new Visita("Ken el cabron", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México",Constants.VISITA_TIPO_PROGRAMADA,"28/6/2016",23,8);
        Visita viernes = new Visita("Eder Padilla", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_FINALIZADA,"29/6/2016",15,9);
        Visita sabado = new Visita("Lucio Sanchez", " Insurgentes Sur, #949 3er piso, despacho 301, Ciudad de México", Constants.VISITA_TIPO_PROGRAMADA,"30/6/2016",16,10);
        Visita diacuatro= new Visita("Puto Ricardo","Vive en la verga",Constants.VISITA_TIPO_PROGRAMADA,"4/6/2016",1);
        visitas.add(diacuatro);
        visitas.add(alguna);
        visitas.add(domingo);
        visitas.add(lunes);
        visitas.add(lunes2);
        visitas.add(lunes3);
        visitas.add(martes);
        visitas.add(miercoles);
        visitas.add(sabado);
        visitas.add(jueves);
        visitas.add(viernes);

        for (int i = 0; i <visitas.size() ; i++) {
            acomodarVisita(visitas.get(i));
        }
        crearVisitas(visitas);
    }
    private void acomodarVisita(Visita visita){
         int hora = horaAsignada(visita);
        compararSemana(visita,hora);
     }
    private void asignarVisita(Visita vPrueba,int diaSemana,int hora){
        switch (diaSemana){
            case 1:
                sundayData.set(hora, vPrueba);
                sundayAdapter.notifyDataSetChanged();
                break;
            case 2:
                mondayData.set(hora, vPrueba);
                mondayAdapter.notifyDataSetChanged();
                break;
            case 3:
                tuesdayData.set(hora, vPrueba);
                tuesdayAdapter.notifyDataSetChanged();
                break;
            case 4:
                wednesdayData.set(hora, vPrueba);
                wednesdayAdapter.notifyDataSetChanged();
                break;
            case 5:
                thursdayData.set(hora, vPrueba);
                thursdayAdapter.notifyDataSetChanged();
                break;
            case 6:
                fridayData.set(hora, vPrueba);
                fridayAdapter.notifyDataSetChanged();
                break;
            case 7:
                saturdayData.set(hora, vPrueba);
                saturdayAdapter.notifyDataSetChanged();
                break;
        }
    } private void compararSemana(Visita vPrueba,int hora){

        if(vPrueba.getDateOfVisit().equals(semanaDomingo)){

            sundayData.set(hora, vPrueba);
            sundayAdapter.notifyDataSetChanged();
        }else if (vPrueba.getDateOfVisit().equals(semanaLunes)){
            mondayData.set(hora, vPrueba);
            mondayAdapter.notifyDataSetChanged();
        }else if (vPrueba.getDateOfVisit().equals(semanaMartes)){
            tuesdayData.set(hora, vPrueba);
            tuesdayAdapter.notifyDataSetChanged();
        }
        else if (vPrueba.getDateOfVisit().equals(semanaMiercoles)){
            wednesdayData.set(hora, vPrueba);
            wednesdayAdapter.notifyDataSetChanged();
        }
        else if (vPrueba.getDateOfVisit().equals(semanaJueves)){
            thursdayData.set(hora, vPrueba);
            thursdayAdapter.notifyDataSetChanged();
        }
        else if (vPrueba.getDateOfVisit().equals(semanaViernes)){
            fridayData.set(hora, vPrueba);
            fridayAdapter.notifyDataSetChanged();
        }
        else if (vPrueba.getDateOfVisit().equals(semanaSabado)){
            saturdayData.set(hora, vPrueba);
            saturdayAdapter.notifyDataSetChanged();
        }
    }


    private void crearVisitas(RealmList<Visita> visita) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(visita);
        realm.commitTransaction();
    }

    @Override
    public String[] fileList() {
        return super.fileList();
    }




    }
