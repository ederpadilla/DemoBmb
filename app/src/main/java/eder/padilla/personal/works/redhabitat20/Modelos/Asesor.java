package eder.padilla.personal.works.redhabitat20.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eder on 25/04/2016.
 */
public class Asesor {

    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public Asesor(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
