package com.github.fcannizzaro.materialstepper;

import android.support.v4.app.Fragment;

import com.github.fcannizzaro.materialstepper.interfaces.Nextable;
import com.github.fcannizzaro.materialstepper.style.BaseStyle;

/**
 * Created by Francesco Cannizzaro on 23/12/2015.
 */
public abstract class AbstractStep extends Fragment implements Nextable {

    protected BaseStyle mStepper;

    public AbstractStep stepper(BaseStyle mStepper) {
        this.mStepper = mStepper;
        return this;
    }

    @Override
    public String optional() {
        return "Optional";
    }

    public abstract String name();

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public void onStepVisible() {
    }

    @Override
    public boolean nextIf() {
        return true;
    }

    @Override
    public String error() {
        return "No error";
    }
}
