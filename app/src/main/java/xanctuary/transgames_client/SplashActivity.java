package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static java.lang.System.gc;

/**
 * Created by Win 7 on 21/03/2016.
 */

public class SplashActivity extends AppCompatActivity {
    ImageView imgText;
    AnimationDrawable frameAnimation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //hilangin title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindowManager();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        //hide action bar
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_splashscreen);

        final Intent intent = new Intent(this, MainActivity.class);


        imgText = (ImageView)findViewById(R.id.textLogo);
        imgText.setBackgroundResource(R.anim.text_animation);
        frameAnimation2 = (AnimationDrawable) imgText.getBackground();

        StartAnimations();

        //handler untuk delay next activity selama 5 detik
        //simulasi load data awal
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    private void StartAnimations() {
        Animation anim;

        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        ImageView iv = (ImageView)findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        RelativeLayout l2 = (RelativeLayout)findViewById(R.id.rel_lay2);
        l2.setVisibility(View.VISIBLE);
        l2.clearAnimation();
        l2.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frameAnimation2.start();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frameAnimation2.stop();
        gc();
    }
}
