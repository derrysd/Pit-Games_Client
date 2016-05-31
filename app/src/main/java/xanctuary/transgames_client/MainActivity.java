package xanctuary.transgames_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gigamole.library.ShadowLayout;
import com.nineoldandroids.animation.Animator;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {
    private ImageView voucherButton, tournamentButton;
    private SliderLayout mDemoSlider;
    private Boolean voucherIsClicked = false, tournamentIsClicked = false;
    private ShadowLayout voucherShadow, tournamentShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voucherShadow = (ShadowLayout) findViewById(R.id.voucherShadow);
        tournamentShadow = (ShadowLayout) findViewById(R.id.tournamentShadow);

        voucherButton = (ImageView) findViewById(R.id.btnVoucher);
        tournamentButton = (ImageView) findViewById(R.id.btnTournament);

        voucherButton.setOnClickListener(onVoucherClicked);
        tournamentButton.setOnClickListener(onTournamentClicked);

        mDemoSlider = (SliderLayout) findViewById(R.id.sliderIklan);
        setupslider(mDemoSlider);

    }



    private void setupslider(SliderLayout mDemoSlider) {
        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("Hannibal",R.drawable.ad1);
        file_maps.put("Big Bang Theory",R.drawable.ad2);
        file_maps.put("House of Cards",R.drawable.ad3);
        file_maps.put("Game of Thrones", R.drawable.ad4);
        file_maps.put("SI Doel",R.drawable.ad5);
        file_maps.put("My Little Pony",R.drawable.ad6);
        file_maps.put("Contoh",R.drawable.ad7);
        file_maps.put("wkkwkwk", R.drawable.ad8);
        file_maps.put("anu",R.drawable.ad9);

        for(String name : file_maps.keySet()){

        TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
        textSliderView
                .description(name)
                .image(file_maps.get(name))
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(sliderClickListener);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            mDemoSlider.addSlider(textSliderView);


        }

        // remember setup first
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setSliderTransformDuration(400, new LinearOutSlowInInterpolator());
        mDemoSlider.addOnPageChangeListener(pageChangeListener);
    }

    private View.OnClickListener onVoucherClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!voucherIsClicked){
            voucherIsClicked = true;
                YoYo.with(Techniques.Pulse)
                        .withListener(voucherListener)
                        .interpolate(new AccelerateInterpolator())
                        .duration(300)
                        .playOn(voucherButton);
            }
        }
    };

    private View.OnClickListener onTournamentClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!tournamentIsClicked){
                //TODO jangan lupa di uncomment
                tournamentIsClicked = true;
                YoYo.with(Techniques.Pulse)
                        .withListener(tournamentListener)
                        .interpolate(new AccelerateInterpolator())
                        .duration(300)
                        .playOn(tournamentButton);
            }
        }
    };

    private Animator.AnimatorListener voucherListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            voucherShadow.setIsShadowed(true);
//            YoYo.with(Techniques.Pulse)
//                    .interpolate(new AccelerateInterpolator())
//                    .duration(300)
//                    .playOn(voucherShadow);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            voucherShadow.setIsShadowed(false);
            Intent intent = new Intent(getApplicationContext(), VoucherActivity2.class);
            startActivity(intent);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            voucherShadow.setIsShadowed(false);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Animator.AnimatorListener tournamentListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            tournamentShadow.setIsShadowed(true);
//            YoYo.with(Techniques.Pulse)
//                    .interpolate(new AccelerateInterpolator())
//                    .duration(300)
//                    .playOn(tournamentShadow);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            tournamentShadow.setIsShadowed(false);
            Toast.makeText(getApplicationContext(), "Tournament Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), VoucherActivity.class);
            startActivity(intent);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            tournamentShadow.setIsShadowed(false);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private BaseSliderView.OnSliderClickListener sliderClickListener = new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView coreSlider) {
            Toast.makeText(coreSlider.getContext(), coreSlider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        }
    };

    private ViewPagerEx.OnPageChangeListener pageChangeListener = new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            String[] trans = {"Default","Accordion", "Background2Foreground","CubeIn", "DepthPage", "Fade",
                    "FlipHorizontal", "FlipPage", "Foreground2Background" , "RotateDown", "RotateUp", "Stack",
                    "Tablet", "ZoomIn", "ZoomOutSlide", "ZoomOut"};
            int idx = new Random().nextInt(trans.length);
            mDemoSlider.setPresetTransformer(trans[idx]);
        }
    };

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        voucherIsClicked = false;
        tournamentIsClicked = false;
        super.onStop();
    }
}