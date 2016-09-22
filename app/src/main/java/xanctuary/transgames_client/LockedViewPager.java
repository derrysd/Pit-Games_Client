package xanctuary.transgames_client;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockedViewPager extends ViewPager{
    public LockedViewPager(Context context) {
        super(context);
    }

    public LockedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //disable swipe
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //disable swipe
        return false;
    }
}
