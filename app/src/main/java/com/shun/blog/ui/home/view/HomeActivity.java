package com.shun.blog.ui.home.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.article.view.NewFragment;
import com.shun.blog.ui.home.presenter.HomePagerAdapter;
import com.shun.blog.ui.user.view.UserFragment;
import com.shun.blog.weights.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_home_rb)
    RadioButton homeHomeRb;
    @BindView(R.id.home_new_rb)
    RadioButton homeNewRb;
    @BindView(R.id.home_user_rb)
    RadioButton homeUserRb;
    @BindView(R.id.home_tab_rg)
    RadioGroup homeTabRg;
    @BindView(R.id.home_vp)
    NoScrollViewPager homeVp;
    private HomePagerAdapter mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }


    @Override
    protected void init() {
        super.init();
        HomeFragment homeFragment = HomeFragment.newInstance();
        UserFragment userFragment = UserFragment.newInstance();
        NewFragment newFragment = NewFragment.newInstance();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(newFragment);
        fragmentList.add(userFragment);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        homeVp.setAdapter(mAdapter);
        homeVp.setCurrentItem(0);
    }


    @OnClick({R.id.home_home_rb, R.id.home_new_rb, R.id.home_user_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_home_rb:
                homeVp.setCurrentItem(0, false);
                break;
            case R.id.home_new_rb:
                homeVp.setCurrentItem(1, false);
                break;
            case R.id.home_user_rb:
                homeVp.setCurrentItem(2, false);
                break;
        }
    }
}
