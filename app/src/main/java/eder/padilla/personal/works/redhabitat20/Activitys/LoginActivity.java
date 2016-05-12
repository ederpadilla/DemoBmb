package eder.padilla.personal.works.redhabitat20.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import org.json.JSONObject;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.interfaces.Interfaz;
import eder.padilla.personal.works.redhabitat20.modelos.Asesor;
import eder.padilla.personal.works.redhabitat20.modelos.Informacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    private DotProgressBar dotProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        hideSystemUI();
        objectInitialization();
        setListeners();
    }


    private void objectInitialization() {
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        dotProgressBar=(DotProgressBar) findViewById(R.id.dot_progress_bar);
        dotProgressBar.setStartColor(getResources().getColor(R.color.peach));
        dotProgressBar.setEndColor(getResources().getColor(R.color.salmon_orange));
    }

    private void setListeners() {
        btnSignUp.setOnClickListener(this);
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        dotProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                submitForm();
                dotProgressBar.setVisibility(View.VISIBLE);
                btnSignUp.setVisibility(View.GONE);

                tryLogIn();

                //Intent myIntent = new Intent(LoginActivity.this, CalendarActivity.class);
                //lasargentoleon LoginActivity.this.startActivity(myIntent);
                break;

        }
    }

    private void tryLogIn() {
        /** Calle the URL where we gona made the peticions. */
        String BASE_URL = "http://192.168.1.97:8080";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Interfaz apiService =
                retrofit.create(Interfaz.class);
        Asesor user = new Asesor(inputEmail.getText().toString(), inputPassword.getText().toString());
        Call<Informacion> call = apiService.createModelo(user);
        call.enqueue(new Callback<Informacion>() {
            @Override
            public void onResponse(Call<Informacion> call, Response<Informacion> response) {
                int statusCode = response.code();
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                JSONObject jsonObject= new JSONObject();


                Informacion user = response.body();
//                System.out.println("El jonson es: "+response.body().toString());
                Log.e("login","entre a onresponse");
                switch (statusCode){
                    case 200:
                        dotProgressBar.setVisibility(View.GONE);
                        System.out.println("El token es "+user.getToken());
                        Log.e("Status es: ",user.getStatus());
                        if(user.getToken()==(null)){
                            Toast toaste = Toast.makeText(context,"Token no recibido", duration);
                            toaste.show();
                        } else {
                            Toast toast = Toast.makeText(context,"Bienvenido: "+ "\n" + user.getName() , duration);
                            toast.show();
                            Intent myIntent = new Intent(LoginActivity.this, CalendarActivity.class);
                            LoginActivity.this.startActivity(myIntent);}
                    break;
                    case 400:
                        dotProgressBar.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.VISIBLE);
                        Toast toast = Toast.makeText(context,"El correo y la contraseña son requeridos" , duration);
                        toast.show();
                        break;
                    case 401:
                        dotProgressBar.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.VISIBLE);
                        Toast toast1 = Toast.makeText(context,"Correo o contraseña inválido" , duration);
                        toast1.show();
                        break;
                    case 403:
                        dotProgressBar.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.VISIBLE);
                        Toast toast2 = Toast.makeText(context, "Confirma tu cuenta antes de iniciar sesión", duration);
                        toast2.show();
                        break;
                    case 404:
                        dotProgressBar.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.VISIBLE);
                        Toast toast3 = Toast.makeText(context, "No se encontró usuario", duration);
                        toast3.show();
                        break;
                    case 500:
                        dotProgressBar.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.VISIBLE);
                        Toast toast4 = Toast.makeText(context, "Server Error", duration);
                        toast4.show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<Informacion> call, Throwable t) {
                dotProgressBar.setVisibility(View.GONE);
                btnSignUp.setVisibility(View.VISIBLE);
                Log.e("login","entre a onfailure");
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                System.out.println("onFailure!!!!!");
                Toast toast = Toast.makeText(context, getResources().getString(R.string.error_server), duration);
                toast.show();


            }
        });

    }

    private void submitForm() {


        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (!isValidEmail2(email)) {
            inputLayoutEmail.setErrorEnabled(true);
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            //requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=\\S+$).{6,20})";

    public  boolean isValidEmail2(String text) {
        if (text.trim().equals("")) {
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            return false;
        }
        return true;
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

}


