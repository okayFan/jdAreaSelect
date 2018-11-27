package com.okayfan.jdareaselect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * author: FYx
 * date:   On 2018/11/14
 */
public class AreaViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList;


    public AreaViewPageAdapter(FragmentManager fm , ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }





}
