package eder.padilla.personal.works.redhabitat20.modelos;

import java.util.Arrays;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 31/03/2016.
 */
/**
 * Clase que define el modelo que se guardara y que se enviara
 */
public class Encuesta extends RealmObject {




    private String informacionQueRecibioSobreLaPropiedad;
    private String contraseña;
    private String elpPorQueCubreoNo;
    private String atencion;
    private String objecionPorLaCulDescartariaelInmueble;
    private String comentarioFinal;
    private String correo;
    private String fecha;
    private String comentarioFinalizarAntes;
    private String comentarioHaciaElPropietario;
    private int    calificacionInmueble;
    private String porQueLoCalificasAsi;
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

    public String getComentarioFinalizarAntes() {
        return comentarioFinalizarAntes;
    }

    public void setComentarioFinalizarAntes(String comentarioFinalizarAntes) {
        this.comentarioFinalizarAntes = comentarioFinalizarAntes;
    }
    public int getCalificacionInmueble() {
        return calificacionInmueble;
    }

    public void setCalificacionInmueble(int calificacionInmueble) {
        this.calificacionInmueble = calificacionInmueble;
    }

    public String getPorQueLoCalificasAsi() {
        return porQueLoCalificasAsi;
    }

    public void setPorQueLoCalificasAsi(String porQueLoCalificasAsi) {
        this.porQueLoCalificasAsi = porQueLoCalificasAsi;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInformacionQueRecibioSobreLaPropiedad() {
        return informacionQueRecibioSobreLaPropiedad;
    }

    public void setInformacionQueRecibioSobreLaPropiedad(String informacionQueRecibioSobreLaPropiedad) {
        this.informacionQueRecibioSobreLaPropiedad = informacionQueRecibioSobreLaPropiedad;
    }
    @Override
    public String toString() {
        return "Encuesta{" +
                "informacionQueRecibioSobreLaPropiedad='" + informacionQueRecibioSobreLaPropiedad + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", elpPorQueCubreoNo='" + elpPorQueCubreoNo + '\'' +
                ", atencion='" + atencion + '\'' +
                ", objecionPorLaCulDescartariaelInmueble='" + objecionPorLaCulDescartariaelInmueble + '\'' +
                ", comentarioFinal='" + comentarioFinal + '\'' +
                ", correo='" + correo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", comentarioFinalizarAntes='" + comentarioFinalizarAntes + '\'' +
                ", comentarioHaciaElPropietario='" + comentarioHaciaElPropietario + '\'' +
                ", calificacionInmueble=" + calificacionInmueble +
                ", porQueLoCalificasAsi='" + porQueLoCalificasAsi + '\'' +
                ", dentrodelpresupuesto=" + dentrodelpresupuesto +
                ", consideraselinmuebledentrodesusopcionesdecompraorenta=" + consideraselinmuebledentrodesusopcionesdecompraorenta +
                ", recibiolainformacionquerequeria=" + recibiolainformacionquerequeria +
                ", elasesoracudiodemanerapuntual=" + elasesoracudiodemanerapuntual +
                ", bytes=" + Arrays.toString(bytes) +
                ", idd=" + idd +
                '}';
    }


}
