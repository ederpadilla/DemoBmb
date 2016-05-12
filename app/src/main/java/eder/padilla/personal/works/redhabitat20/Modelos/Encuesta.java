package eder.padilla.personal.works.redhabitat20.modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 31/03/2016.
 */
/**
 * Clase sobre que se guardara y que se enviara
 */
public class Encuesta extends RealmObject {



    private String contraseña;
    private String elpPorQueCubreoNo;
    private String atencion;
    private String objecionPorLaCulDescartariaelInmueble;
    private String comentarioFinal;
    private String correo;



    private String fecha;


    private String comentarioHaciaElPropietario;
    private boolean cubreRequerimientos;

    private boolean dentrodelpresupuesto;
    private boolean consideraselinmuebledentrodesusopcionesdecompraorenta;
    private boolean recibiolainformacionquerequeria;
    private boolean elasesoracudiodemanerapuntual;


    private byte[]bytes;
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



    public boolean isCubreRequerimientos() {
        return cubreRequerimientos;
    }

    public void setCubreRequerimientos(boolean cubreRequerimientos) {
        this.cubreRequerimientos = cubreRequerimientos;
    }

    public String getElpPorQueCubreoNo() {
        return elpPorQueCubreoNo;
    }

    public void setElpPorQueCubreoNo(String elpPorQueCubreoNo) {
        this.elpPorQueCubreoNo = elpPorQueCubreoNo;
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

    public String getObjecionPorLaCulDescartariaelInmueble() {
        return objecionPorLaCulDescartariaelInmueble;
    }

    public void setObjecionPorLaCulDescartariaelInmueble(String objecionPorLaCulDescartariaelInmueble) {
        this.objecionPorLaCulDescartariaelInmueble = objecionPorLaCulDescartariaelInmueble;
    }

    public String getComentarioFinal() {
        return comentarioFinal;
    }

    public void setComentarioFinal(String comentarioFinal) {
        this.comentarioFinal = comentarioFinal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public String getComentarioHaciaElPropietario() {
        return comentarioHaciaElPropietario;
    }

    public void setComentarioHaciaElPropietario(String comentarioHaciaElPropietario) {
        this.comentarioHaciaElPropietario = comentarioHaciaElPropietario;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
