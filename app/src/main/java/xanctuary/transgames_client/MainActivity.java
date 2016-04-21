package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> images;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter adapter;
    private final static int[] resourceIDs = new int[]{R.drawable.ad1, R.drawable.ad2,
            R.drawable.ad3, R.drawable.ad4, R.drawable.ad5, R.drawable.ad6, R.drawable.ad7, R.drawable.ad8, R.drawable.ad9};


    static ImageView dots[];
    private int dotsCount;
    private LinearLayout dotsLayout;
    public static Boolean isZoomed = false;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>();

        //find view by id
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.container);

        setImagesData();

        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), images);
        viewPager.setAdapter(adapter);

        setDots();

        viewPager.addOnPageChangeListener(onFocusChangeListener(viewPager.getCurrentItem()));

        timerSlide();
    }


    private void timerSlide() {
        handler = new Handler();

        // Create runnable for posting
        runnable = new Runnable() {
            public void run() {

                if (!isZoomed){

                    if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }

                    else {
                        viewPager.setCurrentItem(0);
                    }

                }
            }
        };

        int delay = 5000; // delay 1 detik
        int period = 5000; // ulang setiap 5 detik
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                handler.post(runnable);
            }
        }, delay, period);
    }

    public void onVoucherClicked(View view) {
        Intent intent = new Intent(this, Voucher_1.class);
        startActivity(intent);
    }

    public void onEventClicked(View view) {
        Intent intent = new Intent(this, Event_1.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isZoomed= true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isZoomed = false;
    }

    private void setImagesData() {
        for (int resourceID : resourceIDs) {
            images.add(resourceID);
        }
    }

    private View.OnClickListener onChangePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
            }
        };
    }

    private ViewPager.OnPageChangeListener onFocusChangeListener(final int position) {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageResource(R.drawable.indicator_unselected);
                }
                dots[position].setImageResource(R.drawable.indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    public void setDots(){
        dotsCount = images.size();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.indicator_unselected);
            dots[i].setOnClickListener(onChangePageClickListener(i));
            dots[i].setPadding(5, 0, 5, 5);
            dotsLayout.addView(dots[i]);
        }
        dots[0].setImageResource(R.drawable.indicator_selected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


}