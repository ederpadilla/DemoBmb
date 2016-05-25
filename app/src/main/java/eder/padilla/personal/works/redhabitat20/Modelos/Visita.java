package eder.padilla.personal.works.redhabitat20.modelos;


import java.util.Calendar;

public class Visita
{
     String nombre;
     String direccion;
     String tipo;
     int hora;



    Calendar cal;

    public Visita(String nom, String dir, String ti, int ho, Calendar calendar){
        nombre = nom;
        direccion = dir;
        tipo=ti;
        hora=ho;
        cal=calendar;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }
    public String getTipo() {return tipo;}
    public int getHora(){return hora;}
    public Calendar getCal() {
        return cal;
    }

}
