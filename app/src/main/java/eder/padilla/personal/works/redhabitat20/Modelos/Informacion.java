package eder.padilla.personal.works.redhabitat20.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eder on 16/03/2016.
 */
public class Informacion {
    @SerializedName("token")
    String token;
    @SerializedName("name")
    String name;
    @SerializedName("error")
    String error;

    public Informacion(String token, String name) {
        this.token = token;
        this.name = name;
        this.error=error;
    }

    @Override
    public String toString() {
        return "Info{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getError(){return error;    }
    public void setError(String error){this.error=error; }
}
