package com.shun.blog.ui.home.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shun.blog.R;
import com.shun.blog.api.ApiStores;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.article.view.ArticleListFragment;
import com.shun.blog.ui.home.contract.HomeContract;
import com.shun.blog.ui.home.presenter.HomePagerAdapter;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;
import com.shun.blog.utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenterImpl> implements
        HomeContract.View {

    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.home_tl)
    TabLayout homeTL;

    public HomeFragment() {
        // Required empty public constructor
        mReUse = true;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        List<Fragment> fragmentList = new ArrayList<>();
        ArticleListFragment homeArticle = ArticleListFragment
                .newInstance(ApiStores.list_home);
        fragmentList.add(homeArticle);
        fragmentList.add(ArchiveListFragment.newInstance());
        fragmentList.add(TagFragment.newInstance());
        HomePagerAdapter adapter = new HomePagerAdapter(getFragmentManager(), fragmentList);
        homeVp.setAdapter(adapter);
        homeTL.setupWithViewPager(homeVp);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void refreshTheme() {
        homeTL.setTabTextColors(
                ThemeUtil.getColorId(ThemeUtil.txtNavOff),
                ThemeUtil.getColorId(ThemeUtil.txtNavOn));
        homeTL.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
    }
}
