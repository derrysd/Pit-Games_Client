package xanctuary.transgames_client;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.gigamole.library.ShadowLayout;
import com.github.fcannizzaro.fastevent.EventCallback;
import com.github.fcannizzaro.fastevent.FastEvent;

import java.util.ArrayList;
import java.util.List;

public class VoucherActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private VoucherPagerAdapter pagerAdapter;
    protected TextView error, continueButton, previousButton;
    private ViewSwitcher switcher;
    private List<ShadowLayout> shadowTitle = new ArrayList<>();
    private List<TextView> titleStrip = new ArrayList<>();
    private boolean nextif = false;
    private String errorString = "no error";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        viewPager = (ViewPager) findViewById(R.id.stepPager);
        pagerAdapter = new VoucherPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setPageTransformer(true, new ZoomOutTranformer());

        switcher = (ViewSwitcher) findViewById(R.id.stepSwitcher);
        error = (TextView) findViewById(R.id.stepError);
        previousButton = (TextView) findViewById(R.id.stepPrev);
        continueButton = (TextView) findViewById(R.id.continueButton);
        ShadowLayout shadow1 = (ShadowLayout) findViewById(R.id.shadow1);
        ShadowLayout shadow2 = (ShadowLayout) findViewById(R.id.shadow2);
        ShadowLayout shadow3 = (ShadowLayout) findViewById(R.id.shadow3);
        shadowTitle.add(shadow1);
        shadowTitle.add(shadow2);
        shadowTitle.add(shadow3);

        TextView langkah1 = (TextView) findViewById(R.id.langkah1);
        TextView langkah2 = (TextView) findViewById(R.id.langkah2);
        TextView langkah3 = (TextView) findViewById(R.id.langkah3);
        titleStrip.add(langkah1);
        titleStrip.add(langkah2);
        titleStrip.add(langkah3);

        switcher.setDisplayedChild(0);
        switcher.setInAnimation(VoucherActivity.this, R.anim.in_from_bottom);
        switcher.setOutAnimation(VoucherActivity.this, R.anim.out_to_bottom);

        previousButton.setOnClickListener(onPreviousClicked);
        continueButton.setOnClickListener(onContinueClicked);

        onUpdate();

        FastEvent
                .on("in-activity")
                .onUi(this)
                .execute(new EventCallback() {
                    @Override
                    public void onEvent(Object... args) {
                        errorString = (String) args[1];
                        nextif = (boolean) args[0];
                    }
                });
    }

    private View.OnClickListener onPreviousClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            onUpdate();
        }
    };

    private View.OnClickListener onContinueClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (viewPager.getCurrentItem()==0){
                FastEvent.emit("cartFragment", "ok");
            } else if(viewPager.getCurrentItem()==1){
                FastEvent.emit("isiDataFragment", "ok");
            }
            if(nextif) {
                if (viewPager.getCurrentItem() == 2) {
                    //todo voucher finish
                    Toast.makeText(view.getContext(), "selesai", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    onUpdate();
                }
            } else {
                error.setText(Html.fromHtml(errorString));

                if (switcher.getDisplayedChild() == 0)
                    switcher.setDisplayedChild(1);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (switcher.getDisplayedChild() == 1) switcher.setDisplayedChild(0);
                    }
                }, 1000 + 300);

            }
        }
    };

    public void onUpdate(){
        previousButton.setVisibility(viewPager.getCurrentItem() == 0 ? View.GONE : View.VISIBLE);

        shadowTitle.get(viewPager.getCurrentItem()).setIsShadowed(true);
        titleStrip.get(viewPager.getCurrentItem()).setTypeface(Typeface.DEFAULT_BOLD);
        titleStrip.get(viewPager.getCurrentItem()).setTextColor(ContextCompat.getColor(this, R.color.titleStripColorSelected));

        for(int i = 0; i < viewPager.getChildCount(); i++){

            if(i != viewPager.getCurrentItem()) {
                shadowTitle.get(i).setIsShadowed(false);
                titleStrip.get(i).setTypeface(Typeface.DEFAULT);
                titleStrip.get(i).setTextColor(ContextCompat.getColor(this, R.color.titleStripColorUnselected));
            }

            if (viewPager.getCurrentItem() == viewPager.getChildCount())
                continueButton.setText("FINISH");
            else continueButton.setText("CONTINUE");
        }
    }

    public static class VoucherPagerAdapter extends FragmentPagerAdapter {
        private static int HALAMAN = 3;


        public VoucherPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return HALAMAN;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CartFragment();
                case 1:
                    return new IsiDataFragment2();
                case 2:
                    return new KonfirmasiFragment2();
                default:
                    return null;
            }
        }
    }
}
