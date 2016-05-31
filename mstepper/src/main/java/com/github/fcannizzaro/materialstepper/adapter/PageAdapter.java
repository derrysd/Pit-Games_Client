package com.github.fcannizzaro.materialstepper.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.interfaces.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */

public class PageAdapter extends FragmentPagerAdapter implements Pageable {

    private ArrayList<AbstractStep> fragments = new ArrayList<>();

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void add(AbstractStep fragment) {
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public void set(List<AbstractStep> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @Override
    public AbstractStep getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
