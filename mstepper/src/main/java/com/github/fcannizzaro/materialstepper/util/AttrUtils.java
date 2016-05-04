package com.github.fcannizzaro.materialstepper.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import com.github.fcannizzaro.materialstepper.R;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class AttrUtils {

    private static int getAttr(Context context, int value) {
        TypedValue typedValue = new TypedValue();
        TypedArray style = context.obtainStyledAttributes(typedValue.data, new int[]{value});
        int color = style.getColor(0, 0);
        style.recycle();
        return color;
    }

    public static int getAccent(Context context) {
        return getAttr(context, R.attr.colorAccent);
    }

    public static int getPrimary(Context context) {
        return getAttr(context, R.attr.colorPrimary);
    }

    public static int getPrimaryDark(Context context) {
        return getAttr(context, R.attr.colorPrimaryDark);
    }

}
