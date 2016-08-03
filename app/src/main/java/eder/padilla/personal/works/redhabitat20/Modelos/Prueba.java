package eder.padilla.personal.works.redhabitat20.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import eder.padilla.personal.works.redhabitat20.util.Constants;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 03/08/2016.
 */
public class Prueba {
    @SerializedName(Constants.WEBSERVICE_FIRSTQUESTION)
    private int    calificacionInmueble;
    @SerializedName(Constants.WEBSERVICE_COMENNT)
    private String porQueLoCalificasAsi;
    @SerializedName(Constants.WEBSERVICE_SECONDQUESTION)
    private boolean dentrodelpresupuesto;
    @SerializedName(Constants.WEBSERVICE_THIRDQUESTION)
    private boolean consideraselinmuebledentrodesusopcionesdecompraorenta;
    @SerializedName(Constants.WEBSERVICE_FOURTHQUESTION)
    private String informacionQueRecibioSobreLaPropiedad;
    @SerializedName(Constants.WEBSERVICE_FIFTHQUESTION)
    private boolean elasesoracudiodemanerapuntual;
    @SerializedName(Constants.WEBSERVICE_SIXTHQUESTION)
    private String atencion;
    @SerializedName(Constants.WEBSERVICE_SEVENTHQUESTION)
    private String objecionPorLaCulDescartariaelInmueble;
    @SerializedName(Constants.WEBSERVICE_EIGHTHQUESTION)
    private String comentarioFinal;
    @SerializedName(Constants.WEBSERVICES_WISHOFFERS)
    private boolean deseoOfertas;
    @SerializedName(Constants.WEBSERVICE_IDDVISIT)
    @PrimaryKey
    private int idd;
    @SerializedName("coment")
    private String comentarioDelAsesor;
    @SerializedName("comentariofinal")
    private String comentarioFinalizarAntes;
    @SerializedName("firma")
    private byte[]bytes;



    public boolean isDeseoOfertas() {
        return deseoOfertas;
    }

    public void setDeseoOfertas(boolean deseoOfertas) {
        this.deseoOfertas = deseoOfertas;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
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



    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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

    public String getInformacionQueRecibioSobreLaPropiedad() {
        return informacionQueRecibioSobreLaPropiedad;
    }

    public void setInformacionQueRecibioSobreLaPropiedad(String informacionQueRecibioSobreLaPropiedad) {
        this.informacionQueRecibioSobreLaPropiedad = informacionQueRecibioSobreLaPropiedad;
    }
    public String getComentarioDelAsesor() {
        return comentarioDelAsesor;
    }

    public void setComentarioDelAsesor(String comentarioDelAsesor) {
        this.comentarioDelAsesor = comentarioDelAsesor;
    }
    @Override
    public String toString() {
        return "Prueba{" +
                "calificacionInmueble=" + calificacionInmueble +
                ", porQueLoCalificasAsi='" + porQueLoCalificasAsi + '\'' +
                ", dentrodelpresupuesto=" + dentrodelpresupuesto +
                ", consideraselinmuebledentrodesusopcionesdecompraorenta=" + consideraselinmuebledentrodesusopcionesdecompraorenta +
                ", informacionQueRecibioSobreLaPropiedad='" + informacionQueRecibioSobreLaPropiedad + '\'' +
                ", elasesoracudiodemanerapuntual=" + elasesoracudiodemanerapuntual +
                ", atencion='" + atencion + '\'' +
                ", objecionPorLaCulDescartariaelInmueble='" + objecionPorLaCulDescartariaelInmueble + '\'' +
                ", comentarioFinal='" + comentarioFinal + '\'' +
                ", deseoOfertas=" + deseoOfertas +
                ", idd=" + idd +
                ", comentarioDelAsesor='" + comentarioDelAsesor + '\'' +
                ", comentarioFinalizarAntes='" + comentarioFinalizarAntes + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
