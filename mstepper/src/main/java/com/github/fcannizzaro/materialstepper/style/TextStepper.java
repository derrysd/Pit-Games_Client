package com.github.fcannizzaro.materialstepper.style;

import android.os.Bundle;
import android.widget.TextView;

import com.github.fcannizzaro.materialstepper.R;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class TextStepper extends BaseNavigation {

    // views
    protected TextView mCounter;
    private String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mText = getString(R.string.ms_text_step);

        applyTheme();

        if (getSupportActionBar() != null)
            getSupportActionBar().setElevation(0);

        setContentView(R.layout.style_text);
        init();

        mSwitch.setInAnimation(TextStepper.this, R.anim.in_from_rigth);
        mSwitch.setOutAnimation(TextStepper.this, R.anim.out_to_left);
        mCounter = (TextView) findViewById(R.id.stepCounter);

        onUpdate();
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        int next = mSteps.current() < mSteps.total() - 1 ? mSteps.current() + 1 : mSteps.current();
        mCounter.setText(mText.replace("$current", String.valueOf(next)).replace("$total", String.valueOf(mSteps.total())));
    }

}
