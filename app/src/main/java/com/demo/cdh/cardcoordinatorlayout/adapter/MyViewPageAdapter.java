package com.demo.cdh.cardcoordinatorlayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hang on 2017/4/26.
 */

public class MyViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MyViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments==null? 0: fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments==null? null : fragments.get(position);
    }
}
