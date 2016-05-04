package com.github.fcannizzaro.materialstepper.style;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.R;
import com.github.fcannizzaro.materialstepper.interfaces.Stepable;
import com.github.fcannizzaro.materialstepper.util.AttrUtils;
import com.github.fcannizzaro.materialstepper.util.StepUtils;

import java.util.List;

/**
 * Created by Francesco Cannizzaro on 24/12/2015.
 */
public class BaseStyle extends AppCompatActivity implements Stepable {

    protected StepUtils mSteps = new StepUtils();
    protected Bundle mExtras = new Bundle();

    // attributes
    protected String mTitle;
    protected String mErrorString;

    // properties
    protected int tintColor, primaryColor, primaryColorDark;
    private int mErrorTimeout = 1500;

    // getters
    protected int getColor() {
        return primaryColor;
    }

    protected int getErrorTimeout() {
        return mErrorTimeout;
    }

    protected void setErrorTimeout(int mErrorTimeout) {
        this.mErrorTimeout = mErrorTimeout;
    }

    // setters

    public Bundle getExtras() {
        return mExtras;
    }

    protected void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    protected void setColorPrimary(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    protected void setColorPrimaryDark(int primaryColorDark) {
        this.primaryColorDark = primaryColorDark;
    }

    // steps utils

    protected void addStep(AbstractStep step) {
        mSteps.add(wrap(step));
    }

    protected void addSteps(List<AbstractStep> steps) {
        mSteps.addAll(wrap(steps));
    }

    private void findColors() {

        if (primaryColor == 0)
            primaryColor = AttrUtils.getPrimary(this);

        if (primaryColor == 0)
            primaryColor = ContextCompat.getColor(this, R.color.material_stepper_global);

        if (primaryColorDark == 0)
            primaryColorDark = AttrUtils.getPrimaryDark(this);

        if (primaryColorDark == 0)
            primaryColorDark = ContextCompat.getColor(this, R.color.material_stepper_global_dark);

    }

    protected void applyTheme() {

        findColors();

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(Html.fromHtml(mTitle));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(primaryColor));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                getWindow().setStatusBarColor(primaryColorDark);

        }

        tintColor = ContextCompat.getColor(this, R.color.material_stepper_bottom_bar_text);

    }

    @Override
    public void onPrevious() {
        if (mSteps.current() <= 0)
            return;

        mSteps.current(mSteps.current() - 1);
        onUpdate();
    }

    @Override
    public void onNext() {

        AbstractStep step = mSteps.getCurrent();

        if (!step.isOptional() && !step.nextIf()) {
            mErrorString = step.error();
            onError();
            return;
        }

        if (mSteps.current() == mSteps.total() - 1) {
            Intent intent = new Intent();
            intent.putExtras(mExtras);
            setResult(1, intent);
            onComplete();
            return;
        }

        if (mSteps.current() > mSteps.total() - 1)
            return;

        mSteps.current(mSteps.current() + 1);
        onUpdate();
    }

    public void onComplete() {
        // to be redefined
    }

    @Override
    public void onError() {
        // to be redefined
    }

    @Override
    public void onUpdate() {
        // to be redefined
    }

    // wrap stepper in every step

    private AbstractStep wrap(AbstractStep step) {
        return step.stepper(this);
    }

    private List<AbstractStep> wrap(List<AbstractStep> steps) {
        for (AbstractStep step : steps)
            step.stepper(this);

        return steps;
    }

}
