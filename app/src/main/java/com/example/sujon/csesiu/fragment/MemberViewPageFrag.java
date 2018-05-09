package com.example.sujon.csesiu.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by SuJoN on 5/4/2018.
 */

public class MemberViewPageFrag extends FragmentPagerAdapter{

    private String tabTitles[] = new String[] { "Students", "Teachers", "Alumni" };
    ArrayList<Fragment> fragments = new ArrayList<>();

    public MemberViewPageFrag(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void addFragment(Fragment f)
    {
        fragments.add(f);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
