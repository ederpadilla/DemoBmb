package eder.padilla.personal.works.redhabitat20.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eder on 16/03/2016.
 */
/**Clase para obtener la informacion del usuario.**/
public class Informacion {
    @SerializedName("token")
    String token;
    @SerializedName("name")
    String name;
    @SerializedName("status")
    String status;

    public Informacion(String token, String name,String status) {
        this.token = token;
        this.name = name;
        this.status=status;
    }

    @Override
    public String toString() {
        return "Info{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", name='" + status + '\'' +
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
    public String getStatus(){return status;}
    public void setStatus(){this.status=status;}
}
