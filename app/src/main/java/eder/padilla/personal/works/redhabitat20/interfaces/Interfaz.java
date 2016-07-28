package eder.padilla.personal.works.redhabitat20.interfaces;

import eder.padilla.personal.works.redhabitat20.modelos.Asesor;
import eder.padilla.personal.works.redhabitat20.modelos.Informacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Eder on 16/03/2016.
 */
public interface Interfaz {
    @POST("/app/login")
    Call<Informacion> createModelo(@Body Asesor asesor);
}
