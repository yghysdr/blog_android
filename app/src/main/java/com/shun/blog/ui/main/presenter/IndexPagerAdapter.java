package com.shun.blog.ui.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yghysdr on 2017/5/19.
 */

public class IndexPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public IndexPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOME";
            case 1:
                return "ARCHIVE";
            case 2:
                return "TAG";
        }
        return super.getPageTitle(position);
    }
}
