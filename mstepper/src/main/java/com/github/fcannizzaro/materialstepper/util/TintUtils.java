package com.github.fcannizzaro.materialstepper.util;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.TextView;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class TintUtils {

    public static void tintTextView(TextView textview, int color) {
        Drawable[] drawables = textview.getCompoundDrawables();

        for (int i = 0; i < drawables.length; i++) {

            if (drawables[i] == null)
                continue;

            Drawable wrapDrawable = DrawableCompat.wrap(drawables[i]);
            DrawableCompat.setTintMode(wrapDrawable, PorterDuff.Mode.SRC_ATOP);
            DrawableCompat.setTint(wrapDrawable, color);

            if (i == 0)
                textview.setCompoundDrawables(wrapDrawable, drawables[1], drawables[2], drawables[3]);
            else if (i == 1)
                textview.setCompoundDrawables(drawables[0], wrapDrawable, drawables[2], drawables[3]);
            else if (i == 2)
                textview.setCompoundDrawables(drawables[0], drawables[1], wrapDrawable, drawables[3]);
            else
                textview.setCompoundDrawables(drawables[0], drawables[1], drawables[2], wrapDrawable);
        }


    }

}
