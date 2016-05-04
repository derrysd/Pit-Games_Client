package com.github.fcannizzaro.materialstepper.style;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.github.fcannizzaro.materialstepper.R;


/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class DotStepper extends BaseNavigation {

    // views
    protected LinearLayout mDots;

    // attributes
    private int unselected = Color.parseColor("#bdbdbd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        applyTheme();

        setContentView(R.layout.style_dots);
        mDots = (LinearLayout) findViewById(R.id.dots);

        init();
        onUpdate();

        if (mSteps.total() > 7)
            Log.e("MaterialStepper", "You should use progress bar with so many steps!");
    }

    @Override
    public void onUpdate() {

        Animation scale_in = AnimationUtils.loadAnimation(this, R.anim.scale_in);
        Animation scale_out = AnimationUtils.loadAnimation(this, R.anim.scale_out);

        int i = 0;

        if (mDots.getChildCount() == 0) {

            while (i++ < mSteps.total()) {
                View view = getLayoutInflater().inflate(R.layout.dot, mDots, false);
                view.startAnimation(scale_out);
                mDots.addView(view);
            }

            // prevent see animation at start
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDots.setVisibility(View.VISIBLE);
                }
            }, 500);
        }


        for (i = 0; i < mDots.getChildCount(); i++)

            if (i == mSteps.current() && !mSteps.isActive(i)) {
                mDots.getChildAt(i).startAnimation(scale_in);
                mSteps.setActive(i, true);
                color(i, true);
            } else if (i != mSteps.current() && mSteps.isActive(i)) {
                mDots.getChildAt(i).startAnimation(scale_out);
                mSteps.setActive(i, false);
                color(i, false);
            }

        super.onUpdate();

    }

    private void color(int i, boolean selected) {
        Drawable d = mDots.getChildAt(i).getBackground();
        d.setColorFilter(new PorterDuffColorFilter(selected ? primaryColor : unselected, PorterDuff.Mode.SRC_ATOP));
    }

}
