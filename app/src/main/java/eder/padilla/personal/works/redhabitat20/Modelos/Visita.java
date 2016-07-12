package eder.padilla.personal.works.redhabitat20.modelos;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**Modelo con el que trabajara nuestro recycler view.**/
public class Visita extends RealmObject {
    private String nombre;
    private String direccion;
    private String tipo;
    private String dateOfVisit;
    private int hora;
    @PrimaryKey
    private int idd;


    public Visita(String nombre, String direccion, String tipo, String dateOfVisit, int hora, int idd) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.dateOfVisit = dateOfVisit;
        this.hora = hora;
        this.idd = idd;
    }
    public Visita(String nombre, String dirreccion, String tipodevisita){
        this.nombre = nombre;
        this.direccion = dirreccion;
        this.tipo=tipodevisita;
    }

    public Visita(){

    }
    public Visita(String nombre, String direccion, String tipo, int hora) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.hora = hora;
    }
    public Visita(String nombre, String direccion, String tipo, String dateOfVisit, int hora) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.dateOfVisit = dateOfVisit;
        this.hora = hora;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }
    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }
    @Override
    public String toString() {
        return "Visita{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dateOfVisit='" + dateOfVisit + '\'' +
                ", hora=" + hora +
                ", idd=" + idd +
                '}';
    }
}
