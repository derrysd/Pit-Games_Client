package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> images;
    private BitmapFactory.Options options;
    private ViewPager viewPager;
    private View btnNext, btnPrev;
    private FragmentStatePagerAdapter adapter;
    private final static int[] resourceIDs = new int[]{R.drawable.ad1, R.drawable.ad2,
            R.drawable.ad3, R.drawable.ad4, R.drawable.ad5, R.drawable.ad6, R.drawable.ad7, R.drawable.ad8, R.drawable.ad9};


    static ImageView dots[];
    private int dotsCount;
    private LinearLayout dotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>();

        //find view by id
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.container);

        btnNext = findViewById(R.id.next);
        btnPrev = findViewById(R.id.prev);

        btnPrev.setOnClickListener(onClickListener(0));
        btnNext.setOnClickListener(onClickListener(1));

        setImagesData();

        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), images);
        viewPager.setAdapter(adapter);

        setDots();

        viewPager.addOnPageChangeListener(onFocusChangeListener(adapter.getItemPosition(this)));
    }

    public void onVoucherClicked(View view) {
        Intent intent = new Intent(this, Voucher_1.class);
        startActivity(intent);
    }

    private View.OnClickListener onClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    //next page
                    if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                } else {
                    //previous page
                    if (viewPager.getCurrentItem() > 0) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                }
            }
        };
    }

    private void setImagesData() {
        for (int i = 0; i < resourceIDs.length; i++) {
            images.add(resourceIDs[i]);
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
}