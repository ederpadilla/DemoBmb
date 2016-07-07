package eder.padilla.personal.works.redhabitat20.modelos;


import java.util.Calendar;
/**Modelo con el que trabajara nuestro recycler view.**/
public class Visita
{
    String nombre;
    String direccion;
    String tipo;
    int hora;
    Calendar calendar;

    public Visita(String nombre, String dirreccion, String tipodevisita, int hora, Calendar calendar){
        this.nombre = nombre;
        this.direccion = dirreccion;
        this.tipo=tipodevisita;
        this.hora=hora;
        this.calendar =calendar;
    }
    public Visita(String nombre, String dirreccion, String tipodevisita, int hora){
        this.nombre = nombre;
        this.direccion = dirreccion;
        this.tipo=tipodevisita;
        this.hora=hora;
    }
    public Visita(String nombre, String dirreccion, String tipodevisita){
        this.nombre = nombre;
        this.direccion = dirreccion;
        this.tipo=tipodevisita;
    }
    public Visita(){}

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }
    public String getTipo() {return tipo;}
    public int getHora(){return hora;}
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
