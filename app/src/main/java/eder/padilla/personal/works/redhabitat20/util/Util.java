package eder.padilla.personal.works.redhabitat20.util;


import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;

/**
 * Created by Eder on 23/03/2016.
 */
public class Util {

    public static String  toStringEncuesta(Encuesta e){
        return "Encuesta{" +
                "correo='" + e.getCorreo() + '\'' +
                ", contraseña='" + e.getContraseña() + '\'' +
                ", elporquecubreono='" + e.getElpPorQueCubreoNo() + '\'' +
                ", dentrodelpresupuesto=" + e.isDentrodelpresupuesto() +'\'' +
                ", consideraselinmuebledentrodesusopcionesdecompraorenta=" + e.isConsideraselinmuebledentrodesusopcionesdecompraorenta() +'\'' +
                ", recibiolainformacionquerequeria=" + e.isRecibiolainformacionquerequeria() +'\'' +
                ", elasesoracudiodemanerapuntual=" + e.isElasesoracudiodemanerapuntual() +'\'' +
                ", atencion='" + e.getAtencion() + '\'' +
                ", objecionporlaculdescartariaelinmueble='" + e.getObjecionPorLaCulDescartariaelInmueble() + '\'' +
                ", etComentarioFinal='" + e.getComentarioFinal() + '\'' +

                ", idd=" + e.getIdd() +
                '}';

    }
}
