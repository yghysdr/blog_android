package io.yghysdr.main.view;

import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.BaseActivity;
import com.github.yghysdr.theme.ThemeEvent;
import com.github.yghysdr.util.PreUtils;
import com.github.yghysdr.util.UIUtils;
import com.github.yghysdr.base.AppManager;
import com.github.yghysdr.widget.NoScrollViewPager;

import com.github.yghysdr.theme.ThemeUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.yghysdr.main.R;
import io.yghysdr.main.R2;
import io.yghysdr.main.contract.MainContract;
import io.yghysdr.main.presenter.MainPagerAdapter;
import io.yghysdr.main.presenter.MainPresenterImpl;
import io.yghysdr.mediator.discover.MediatorDiscover;
import io.yghysdr.mediator.user.MediatorUser;

public class MainActivity extends BaseActivity
        implements MainContract.View {

    @BindView(R2.id.main_vp)
    NoScrollViewPager mainVp;
    @BindView(R2.id.main_tb)
    TabLayout mainTb;

    private MainPagerAdapter mAdapter;
    private MainPresenterImpl mMainPresenter;


    @Override
    public void addPresenter(List basePresenters) {
        mMainPresenter = new MainPresenterImpl();
        basePresenters.add(mMainPresenter);
    }

    @Override
    protected int provideContentViewId() {
        if (Build.VERSION.SDK_INT <= 21) {
            return R.layout.main_activity_main_k;
        } else {
            return R.layout.mian_activity_main;
        }
    }

    @Override
    public void initData() {
        super.initData();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(MediatorDiscover.getDiscoverFragment());
        fragmentList.add(MediatorUser.getUserFragment());
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setOffscreenPageLimit(fragmentList.size());
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

        if (PreUtils.getBoolean(BaseApp.getContext(), ThemeUtil.THEME_NORMAL, true)) {
            ThemeUtil.initTheme(this, R.style.AppTheme);
        } else {
            ThemeUtil.initTheme(this, R.style.AppThemeNight);
        }
        refreshStatusBar();
        changeBotBar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventTheme(ThemeEvent event) {
        switch (event.intent) {
            case ThemeEvent.CHANGE:
                refreshStatusBar();
                changeBotBar();
        }
    }

    private void changeBotBar() {
        mainTb.setTabTextColors(
                ThemeUtil.getColorId(this, ThemeUtil.txtNavBotOff),
                ThemeUtil.getColorId(this, ThemeUtil.txtNavBotOn));
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

    private long currentTime;

    @Override
    public void onBackPressed() {
        if (2000 > System.currentTimeMillis() - currentTime) {
            AppManager.getAppManager().AppExit(this, false);
        }
        UIUtils.showToast(BaseApp.getContext(), getString(R.string.toast_exit_app));
        currentTime = System.currentTimeMillis();
    }


}
