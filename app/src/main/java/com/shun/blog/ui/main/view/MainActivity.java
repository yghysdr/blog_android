package com.shun.blog.ui.main.view;

import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.discover.view.DiscoverFragment;
import com.shun.blog.ui.home.view.HomeFragment;
import com.shun.blog.ui.main.presenter.MainPagerAdapter;
import com.shun.blog.ui.main.contract.MainContract;
import com.shun.blog.ui.main.presenter.MainPresenterImpl;
import com.shun.blog.ui.user.view.UserFragment;
import com.shun.blog.utils.ThemeUtil;
import com.shun.blog.weights.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenterImpl>
        implements MainContract.View {

    @BindView(R.id.main_vp)
    NoScrollViewPager mainVp;
    @BindView(R.id.main_tb)
    TabLayout mainTb;

    private MainPagerAdapter mAdapter;

    @Override
    public int getLayoutResource() {
        if (Build.VERSION.SDK_INT <= 21) {
            return R.layout.activity_main_k;
        } else {
            return R.layout.activity_main;
        }
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
        mainTb.setupWithViewPager(mainVp);
        mainTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainVp.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mainVp.setAdapter(mAdapter);
    }

    @Override
    protected void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        mSlidBack = false;
    }

    @Override
    public void changeTheme() {
        refreshStatusBar();
        changeBotBar();
    }

    private void changeBotBar() {
        mainTb.setTabTextColors(
                ThemeUtil.getColorId(ThemeUtil.txtNavBotOff),
                ThemeUtil.getColorId(ThemeUtil.txtNavBotOn));
        mainTb.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgNavBot));
    }

    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }
}
