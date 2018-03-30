package com.shun.blog.ui.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shun.blog.App;
import com.shun.blog.R;

import java.util.List;

/**
 * Created by yghysdr on 2017/5/18.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
                return App.getResString(R.string.bar_home);
            case 1:
                return App.getResString(R.string.bar_discover);
            case 2:
                return App.getResString(R.string.bar_my);
        }
        return super.getPageTitle(position);
    }
}
