package eder.padilla.personal.works.redhabitat20.Fragments;


import eder.padilla.personal.works.redhabitat20.Modelos.Encuesta;

/**
 * Created by Eder on 23/03/2016.
 */
public class Util {

    public static String  toStringEncuesta(Encuesta e){
        return "Encuesta{" +
                "correo='" + e.getCorreo() + '\'' +
                ", contraseña='" + e.getContraseña() + '\'' +
                ", cubrerequerimientos=" + e.isCubrerequerimientos() + '\'' +
                ", elporquecubreono='" + e.getElporquecubreono() + '\'' +
                ", dentrodelpresupuesto=" + e.isDentrodelpresupuesto() +'\'' +
                ", consideraselinmuebledentrodesusopcionesdecompraorenta=" + e.isConsideraselinmuebledentrodesusopcionesdecompraorenta() +'\'' +
                ", recibiolainformacionquerequeria=" + e.isRecibiolainformacionquerequeria() +'\'' +
                ", elasesoracudiodemanerapuntual=" + e.isElasesoracudiodemanerapuntual() +'\'' +
                ", atencion='" + e.getAtencion() + '\'' +
                ", objecionporlaculdescartariaelinmueble='" + e.getObjecionporlaculdescartariaelinmueble() + '\'' +
                ", etComentarioFinal='" + e.getComentariofinal() + '\'' +

                ", idd=" + e.getIdd() +
                '}';

    }
}
