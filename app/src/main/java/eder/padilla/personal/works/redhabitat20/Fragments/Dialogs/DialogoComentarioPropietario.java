package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Eder on 10/04/2016.
 */
public class DialogoComentarioPropietario extends DialogFragment implements View.OnClickListener {
    private Button mbt_relizar_comentario_propietario;
    private Button mbt_no_realizar_comentario;
    private EditText met_ComentarioHaciaElPropietario;
    private RealmResults<Encuesta> allEncuestas;
    private Realm realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflamos nuestra vista  */
        View view = inflater.inflate(R.layout.dialogo_comentario_propietario, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        objectInitialization(view);
        setListeners();
        return view;
    }

    /* Metodo para referenciar nuestros objetos */
    private void objectInitialization(View view) {
        mbt_relizar_comentario_propietario = (Button) view.findViewById(R.id.bt_dialogo_aceptar);
        mbt_no_realizar_comentario =(Button) view.findViewById(R.id.bt__dialogo_cancelar);
        met_ComentarioHaciaElPropietario=(EditText)view.findViewById(R.id.et_cometario_propietario);
        realm = Realm.getInstance(getActivity().getApplicationContext());
        allEncuestas = realm.where(Encuesta.class).findAll();


    }
    /* Asignamos listeners a los botones */
    public void setListeners(){
        mbt_no_realizar_comentario.setOnClickListener(this);
        mbt_relizar_comentario_propietario.setOnClickListener(this);
    }
    /* Asignamos funcionalidades al hacer click en botones */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.bt_dialogo_aceptar:
            ((MainActivity) getActivity()).encuesta.setIdd(allEncuestas.size() + 1);

            ((MainActivity)getActivity()).encuesta.setComentarioHaciaElPropietario
                    (met_ComentarioHaciaElPropietario.getText().toString());

            dismiss();
        break;
            case R.id.bt__dialogo_cancelar:
                ((MainActivity) getActivity()).encuesta.setIdd(allEncuestas.size() + 1);

                dismiss();
                break;
    }
    }

}
