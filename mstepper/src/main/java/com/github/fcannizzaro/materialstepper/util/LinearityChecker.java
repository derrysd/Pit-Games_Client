package com.github.fcannizzaro.materialstepper.util;

import java.util.ArrayList;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class LinearityChecker {

    public ArrayList<Boolean> mDone = new ArrayList<>();

    public LinearityChecker(int steps) {
        while (steps-- > 0)
            mDone.add(false);
    }

    public boolean beforeDone(int i) {
        return i > 0 ? mDone.get(i - 1) : mDone.get(0);
    }

    public boolean isDone(int i) {
        return mDone.get(i);
    }

    public void setDone(int i) {
        mDone.set(i > 0 ? i - 1 : 0, true);
    }

}
