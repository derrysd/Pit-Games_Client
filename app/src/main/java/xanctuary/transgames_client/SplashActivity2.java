package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.System.gc;

public class SplashActivity2 extends AppCompatActivity {
    AnimationDrawable frameAnimation1;
    AnimationDrawable frameAnimation2;
    ImageView imgLogo;
    ImageView imgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //hilangin title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindowManager();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.

        imgLogo = (ImageView)findViewById(R.id.ImageViewLogo);
        imgLogo.setBackgroundResource(+R.anim.logo_animation);


        imgText = (ImageView)findViewById(R.id.ImageViewText);
        imgText.setVisibility(View.GONE);


        // Get the background, which has been compiled to an AnimationDrawable object.

        frameAnimation1 = (AnimationDrawable) imgLogo.getBackground();


        final Intent intent = new Intent(this, MainActivity.class);

        //handler untuk delay next activity selama 5 detik
        //simulasi load data awal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 5000);

        //delay untuk teks
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StartAnimations();
            }
        }, 2000);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        frameAnimation1.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frameAnimation1.stop();
        frameAnimation2.stop();
        gc();
    }

    private void StartAnimations() {
        Animation anim;
        imgText.setVisibility(View.VISIBLE);
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        imgText.clearAnimation();
        imgText.startAnimation(anim);
        imgText.setBackgroundResource(+R.anim.text_animation);
        frameAnimation2 = (AnimationDrawable) imgText.getBackground();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frameAnimation2.start();
            }
        }, 2000);

    }
}
