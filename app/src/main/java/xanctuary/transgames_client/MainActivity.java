package xanctuary.transgames_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDemoSlider = (SliderLayout)findViewById(R.id.sliderIklan);

        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("Hannibal",R.drawable.ad1);
        file_maps.put("Big Bang Theory",R.drawable.ad2);
        file_maps.put("House of Cards",R.drawable.ad3);
        file_maps.put("Game of Thrones", R.drawable.ad4);
        file_maps.put("SI Doel",R.drawable.ad5);
        file_maps.put("My Little Pony",R.drawable.ad6);
        file_maps.put("Gang Bang",R.drawable.ad7);
        file_maps.put("wkkwkwk", R.drawable.ad8);
        file_maps.put("anu",R.drawable.ad9);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    public void onVoucherClicked(View view) {
        Intent intent = new Intent(this, VoucherActivity2.class);
        startActivity(intent);
    }

    public void onTournamentClicked(View view) {
        Intent intent = new Intent(this, VoucherActivity.class);
        startActivity(intent);
    }

    public void onVoucherTipe3(View view) {
        Intent intent = new Intent(this, VoucherActivity3.class);
        startActivity(intent);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

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

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


}