package io.yghysdr.main.view;

import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.github.yghysdr.base.ui.BaseActivity;
import com.github.yghysdr.base.utils.UIUtils;
import com.github.yghysdr.base.widget.NoScrollViewPager;
import io.yghysdr.common.AppManager;
import io.yghysdr.common.common.util.ThemeUtil;
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
            return R.layout.activity_main_k;
        } else {
            return R.layout.activity_main;
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

    private long currentTime;

    @Override
    public void onBackPressed() {
        if (2000 > System.currentTimeMillis() - currentTime) {
            AppManager.getAppManager().AppExit(this, false);
        }
        UIUtils.showToast(getString(R.string.toast_exit_app));
        currentTime = System.currentTimeMillis();
    }


}
