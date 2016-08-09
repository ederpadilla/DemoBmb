package eder.padilla.personal.works.redhabitat20.interfaces;

import java.util.ArrayList;

import eder.padilla.personal.works.redhabitat20.modelos.Asesor;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import eder.padilla.personal.works.redhabitat20.modelos.Informacion;

import eder.padilla.personal.works.redhabitat20.modelos.Prueba;
import eder.padilla.personal.works.redhabitat20.util.Constants;
import io.realm.RealmList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Eder on 16/03/2016.
 */
public interface InterfazPeticiones {
    @POST(Constants.KEY_LOGIN)
    Call<Informacion> mandarUsuario(@Body Asesor asesor);
    @GET(Constants.KEY_GETVISITS)
    Call<ResponseBody> obtenerVisitasdelBack(@Query(Constants.KEY_DATE)String fechaActual,@Header(Constants.KEY_AUTHORIZATION)String token);
    @POST (Constants.KEY_SENDVISITS)
    Call<ResponseBody> mandarEncuestas(@Header(Constants.KEY_AUTHORIZATION)String token,@Body RealmList<Encuesta> encuestas);
}