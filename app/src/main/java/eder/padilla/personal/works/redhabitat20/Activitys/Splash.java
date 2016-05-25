package eder.padilla.personal.works.redhabitat20.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import eder.padilla.personal.works.redhabitat20.R;
import eder.padilla.personal.works.redhabitat20.util.Constants;

public class Splash extends Activity {


    private SharedPreferences mSharedPreferences;

    private Thread splashTread;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSharedPreferences =this.getSharedPreferences("Login",0);

        StartAnimations();


    }
    /** Here we star the animation. */
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        /** We call the animation that says form where to where its gonna move. */
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        /** The image ots gona move.**/
        ImageView logo = (ImageView) findViewById(R.id.splash);
        logo.clearAnimation();
        logo.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {

                    /** Pause the execution of the code for 3.5 secs. **/
                    sleep(3500);
                    /** We check if there is a user log in or not. **/
                    if(mSharedPreferences.getString(Constants.PREFERENCES_USER_NAME,null).length()>0){

                        Log.e("entra al if","if false");
                        Intent intent = new Intent(Splash.this,
                                CalendarActivity.class);intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);

                    }else{
                        Log.e("entra al else","else");
                        Intent intent = new Intent(Splash.this,
                                LoginActivity.class);intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        }


                    Splash.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash.this.finish();
                }
            }
        };
        splashTread.start();

    }

}