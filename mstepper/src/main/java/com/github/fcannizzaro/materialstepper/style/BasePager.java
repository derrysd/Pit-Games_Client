package com.github.fcannizzaro.materialstepper.style;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.R;
import com.github.fcannizzaro.materialstepper.adapter.PageAdapter;
import com.github.fcannizzaro.materialstepper.adapter.PageChangeAdapter;
import com.github.fcannizzaro.materialstepper.adapter.PageStateAdapter;
import com.github.fcannizzaro.materialstepper.interfaces.Pageable;

import java.util.List;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class BasePager extends BaseStyle {

    // view
    protected ViewPager mPager;

    // adapters
    protected Pageable mPagerAdapter;

    protected void init() {
        mPager = (ViewPager) findViewById(R.id.stepPager);
        assert mPager != null;
        mPager.setAdapter((PagerAdapter) mPagerAdapter);
        mSteps.get(0).onStepVisible();
        mPager.addOnPageChangeListener(new PageChangeAdapter() {
            @Override
            public void onPageSelected(int position) {
                mSteps.get(position).onStepVisible();
            }
        });
    }

    private void initAdapter() {
        if (mPagerAdapter == null)
            mPagerAdapter = getStateAdapter() ? new PageStateAdapter(getSupportFragmentManager()) : new PageAdapter(getSupportFragmentManager());
    }

    @Override
    public void addStep(AbstractStep step) {
        super.addStep(step);
        initAdapter();
        mPagerAdapter.add(step);
    }

    @Override
    public void addSteps(List<AbstractStep> steps) {
        super.addSteps(steps);
        initAdapter();
        mPagerAdapter.set(mSteps.getSteps());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        mPager.setCurrentItem(mSteps.current());
    }

}
