package eder.padilla.personal.works.redhabitat20.fragments.dialogs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;


import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;

import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.R;

/**
 * Created by Eder on 06/04/2016.
 */
/* Clase encargada de la firma del cliente */
public class Firma_Electronica extends DialogFragment implements SignaturePad.OnSignedListener, View.OnClickListener{
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private ImageButton close;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflamos nuestra vista */
        View view = inflater.inflate(R.layout.dialogo_firma_electronica, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        objectInitialization(view);

        setListeners();
        return view;
    }
    /* Referenciamos nuestros objetos */
    public void objectInitialization(View view){
        mClearButton = (Button) view.findViewById(R.id.clear_button);
        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mSignaturePad = (SignaturePad) view.findViewById(R.id.signature_pad);
        close=(ImageButton)view.findViewById(R.id.cerrar);
    }

    private void setListeners(){
        mSignaturePad.setOnSignedListener(this);
        mClearButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);
        close.setOnClickListener(this);
    }
    /* Se guarda la firma  */
    public void saveSignature()  {
        Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ((MainActivity) getActivity()).encuesta.setBytes(stream.toByteArray());
    }




    @Override
    public void onStartSigning() {

    }

    @Override
    public void onSigned() {
        mSaveButton.setEnabled(true);
        mClearButton.setEnabled(true);

    }
    /* Se borra la firma  */
    @Override
    public void onClear() {
        mSaveButton.setEnabled(false);
        mClearButton.setEnabled(false);

    }
    /* Asignamos comportamientos a nuestros botones */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_button:
                mSignaturePad.clear();
                break;
            case R.id.save_button:
                saveSignature();
                dismiss();
                break;
            case R.id.cerrar:
                dismiss();

                break;

        }

    }

}
