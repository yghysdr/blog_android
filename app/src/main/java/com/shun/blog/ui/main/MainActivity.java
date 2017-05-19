package com.shun.blog.ui.main;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shun.blog.Jump;
import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.discover.view.DiscoverFragment;
import com.shun.blog.ui.home.view.HomeFragment;
import com.shun.blog.ui.user.view.UserFragment;
import com.shun.blog.utils.UserData;
import com.shun.blog.weights.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_vp)
    NoScrollViewPager mainVp;
    @BindView(R.id.main_home_rb)
    RadioButton mainHomeRb;
    @BindView(R.id.main_new_rb)
    RadioButton mainNewRb;
    @BindView(R.id.main_user_rb)
    RadioButton mainUserRb;
    @BindView(R.id.main_tab_rg)
    RadioGroup mainTabRg;
    private MainPagerAdapter mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mFinishAnim = false;
        HomeFragment homeFragment = HomeFragment.newInstance();
        DiscoverFragment discoverFragment = DiscoverFragment.newInstance();
        UserFragment userFragment = UserFragment.newInstance();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(userFragment);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setAdapter(mAdapter);
        mainVp.setCurrentItem(0);
    }

    @OnClick({R.id.main_home_rb, R.id.main_new_rb, R.id.main_user_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_home_rb:
                mainVp.setCurrentItem(0, false);
                break;
            case R.id.main_new_rb:
                mainVp.setCurrentItem(1, false);
                break;
            case R.id.main_user_rb:
                if (UserData.getCurrentUser() == null) {
                    Jump.login(this);
                } else {
                    mainVp.setCurrentItem(2, false);
                }
                break;
        }
    }
}
