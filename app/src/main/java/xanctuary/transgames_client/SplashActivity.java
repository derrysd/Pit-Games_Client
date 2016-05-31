package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eftimoff.androipathview.PathView;
import com.nineoldandroids.animation.Animator;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class SplashActivity extends AppCompatActivity  {
    private LinearLayout revealLayout;
    private ShimmerTextView logoText1, logoText2, logoText3;
    private PathView pathView;
    private TextView taglineText;
    private Boolean alreadyRun = false;

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

        revealLayout = (LinearLayout) findViewById(R.id.reveal);
        revealLayout.setVisibility(View.INVISIBLE);
        pathView = (PathView) findViewById(R.id.pathView);
//        kenBurnsView = (KenBurnsView) findViewById(R.id.kenBurns);

        logoText1 = (ShimmerTextView) findViewById(R.id.teksLogokiri);
        logoText2 = (ShimmerTextView) findViewById(R.id.teksLogoTengah);
        logoText3 = (ShimmerTextView) findViewById(R.id.teksLogokanan);
        Typeface fontLogo = Typeface.createFromAsset(getAssets(), "fonts/CinzelDecorative-Black.otf");
        logoText1.setTypeface(fontLogo);
        logoText2.setTypeface(fontLogo);
        logoText3.setTypeface(fontLogo);

        taglineText = (TextView) findViewById(R.id.tagline);
        Typeface fontTagline = Typeface.createFromAsset(getAssets(), "fonts/simfang.ttf");
        taglineText.setTypeface(fontTagline);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && !alreadyRun) {
            int cx = (revealLayout.getLeft() + (revealLayout.getRight() / 2));
            int cy = revealLayout.getBottom();
            int endradius = Math.max(revealLayout.getWidth(), revealLayout.getHeight());
            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0, endradius);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(1000);
            revealLayout.setVisibility(View.VISIBLE);
            animator.addListener(revealListener);
            animator.start();

            YoYo.with(Techniques.FadeOut)
                    .duration(100)
                    .playOn(taglineText);
            YoYo.with(Techniques.FadeOut)
                    .duration(100)
                    .playOn(logoText1);
            YoYo.with(Techniques.FadeOut)
                    .duration(100)
                    .playOn(logoText2);
            YoYo.with(Techniques.FadeOut)
                    .duration(100)
                    .playOn(logoText3);
        }
    }

    private SupportAnimator.AnimatorListener revealListener = new SupportAnimator.AnimatorListener() {
        @Override
        public void onAnimationStart() {
            alreadyRun = true;
        }

        @Override
        public void onAnimationEnd() {
            pathView.getPathAnimator()
                    .delay(100)
                    .duration(1500)
                    .interpolator(new AccelerateInterpolator())
                    .listenerEnd(pathViewListener)
                    .start();
            pathView.useNaturalColors();
            pathView.setFillAfter(true);
        }

        @Override
        public void onAnimationCancel() {

        }

        @Override
        public void onAnimationRepeat() {

        }
    };

    private PathView.AnimatorBuilder.ListenerEnd pathViewListener = new PathView.AnimatorBuilder.ListenerEnd() {
        @Override
        public void onAnimationEnd() {
            YoYo.with(Techniques.Pulse)
                    .duration(500)
                    .interpolate(new AccelerateInterpolator())
                    .withListener(pulseInListener)
                    .playOn(pathView);
        }
    };

    private Animator.AnimatorListener pulseInListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            YoYo.with(Techniques.FlipInX)
                    .duration(500)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            YoYo.with(Techniques.FlipInX)
                                    .duration(500)
                                    .withListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            YoYo.with(Techniques.FlipInX)
                                                    .duration(500)
                                                    .withListener(flipInListener)
                                                    .playOn(logoText3);
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    })
                                    .playOn(logoText2);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(logoText1);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Animator.AnimatorListener flipInListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Shimmer shimmer1 = new Shimmer();
            shimmer1.setRepeatCount(0)
                    .setDuration(500)
                    .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
            shimmer1.start(logoText1);

            Shimmer shimmer2 = new Shimmer();
            shimmer2.setRepeatCount(0)
                    .setDuration(500)
                    .setStartDelay(200)
                    .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
            shimmer2.start(logoText2);

            Shimmer shimmer3 = new Shimmer();
            shimmer3.setRepeatCount(0)
                    .setDuration(500)
                    .setStartDelay(500)
                    .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
            shimmer3.setAnimatorListener(shimmerListener);
            shimmer3.start(logoText3);


        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private android.animation.Animator.AnimatorListener shimmerListener = new android.animation.Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(android.animation.Animator animator) {

        }

        @Override
        public void onAnimationEnd(android.animation.Animator animator) {
            YoYo.with(Techniques.SlideInUp)
                    .duration(1000)
                    .withListener(slideInListener)
                    .playOn(taglineText);
        }

        @Override
        public void onAnimationCancel(android.animation.Animator animator) {

        }

        @Override
        public void onAnimationRepeat(android.animation.Animator animator) {
        }
    };

    private Animator.AnimatorListener slideInListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}
