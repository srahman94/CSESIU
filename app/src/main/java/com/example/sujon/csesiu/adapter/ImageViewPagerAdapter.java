package com.example.sujon.csesiu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by SuJoN on 5/5/2018.
 */

public class ImageViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;

    public ImageViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> listFragment) {
        super(fragmentManager);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
