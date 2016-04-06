package eder.padilla.personal.works.redhabitat20.Modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 31/03/2016.
 */
public class Encuesta extends RealmObject {



    private String contraseña;
    private boolean cubrerequerimientos;
    private String elporquecubreono;
    private boolean dentrodelpresupuesto;
    private boolean consideraselinmuebledentrodesusopcionesdecompraorenta;
    private boolean recibiolainformacionquerequeria;
    private boolean elasesoracudiodemanerapuntual;
    private String atencion;
    private String objecionporlaculdescartariaelinmueble;
    private String comentariofinal;
    private String correo;
    @PrimaryKey
    private int idd;

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }



    public boolean isCubrerequerimientos() {
        return cubrerequerimientos;
    }

    public void setCubrerequerimientos(boolean cubrerequerimientos) {
        this.cubrerequerimientos = cubrerequerimientos;
    }

    public String getElporquecubreono() {
        return elporquecubreono;
    }

    public void setElporquecubreono(String elporquecubreono) {
        this.elporquecubreono = elporquecubreono;
    }

    public boolean isDentrodelpresupuesto() {
        return dentrodelpresupuesto;
    }

    public void setDentrodelpresupuesto(boolean dentrodelpresupuesto) {
        this.dentrodelpresupuesto = dentrodelpresupuesto;
    }

    public boolean isConsideraselinmuebledentrodesusopcionesdecompraorenta() {
        return consideraselinmuebledentrodesusopcionesdecompraorenta;
    }

    public void setConsideraselinmuebledentrodesusopcionesdecompraorenta(boolean consideraselinmuebledentrodesusopcionesdecompraorenta) {
        this.consideraselinmuebledentrodesusopcionesdecompraorenta = consideraselinmuebledentrodesusopcionesdecompraorenta;
    }

    public boolean isRecibiolainformacionquerequeria() {
        return recibiolainformacionquerequeria;
    }

    public void setRecibiolainformacionquerequeria(boolean recibiolainformacionquerequeria) {
        this.recibiolainformacionquerequeria = recibiolainformacionquerequeria;
    }

    public boolean isElasesoracudiodemanerapuntual() {
        return elasesoracudiodemanerapuntual;
    }

    public void setElasesoracudiodemanerapuntual(boolean elasesoracudiodemanerapuntual) {
        this.elasesoracudiodemanerapuntual = elasesoracudiodemanerapuntual;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getObjecionporlaculdescartariaelinmueble() {
        return objecionporlaculdescartariaelinmueble;
    }

    public void setObjecionporlaculdescartariaelinmueble(String objecionporlaculdescartariaelinmueble) {
        this.objecionporlaculdescartariaelinmueble = objecionporlaculdescartariaelinmueble;
    }

    public String getComentariofinal() {
        return comentariofinal;
    }

    public void setComentariofinal(String comentariofinal) {
        this.comentariofinal = comentariofinal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
