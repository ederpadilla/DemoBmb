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

public class Splash extends Activity {
    SharedPreferences sp1;
    /** Called when the activity is first created. */
    Thread splashTread;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp1=this.getSharedPreferences("Login",0);

        StartAnimations();


    }
    /** Here we star the animation. */
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    if(sp1.getString("Unm",null).length()>0){
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