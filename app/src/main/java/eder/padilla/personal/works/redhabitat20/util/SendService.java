package eder.padilla.personal.works.redhabitat20.util;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import eder.padilla.personal.works.redhabitat20.activitys.MainActivity;
import eder.padilla.personal.works.redhabitat20.interfaces.InterfazPeticiones;
import eder.padilla.personal.works.redhabitat20.modelos.Encuesta;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eder on 04/08/2016.
 */
public class SendService extends Service {
//    Realm realm = Realm.getDefaultInstance();




//    SharedPreferences userDetails;
  //  public String token= userDetails.getString(Constants.TOKEN_FINAL, "");

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Entra a OnStartComand","hola desde onstart comand");
        return super.onStartCommand(intent, flags, startId);
    }




}
