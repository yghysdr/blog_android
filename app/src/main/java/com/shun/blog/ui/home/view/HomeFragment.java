package com.shun.blog.ui.home.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shun.blog.R;
import com.shun.blog.api.ApiStores;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.article.view.ArticleListFragment;
import com.shun.blog.ui.home.presenter.HomePagerAdapter;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenterImpl> {

    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.home_tl)
    TabLayout tabLayout;

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
        ArticleListFragment androidArticle = ArticleListFragment
                .newInstance(ApiStores.list_android);
        ArticleListFragment webArticle = ArticleListFragment
                .newInstance(ApiStores.list_web);
        fragmentList.add(homeArticle);
        fragmentList.add(androidArticle);
        fragmentList.add(webArticle);
        HomePagerAdapter adapter = new HomePagerAdapter(getFragmentManager(), fragmentList);
        homeVp.setAdapter(adapter);
        tabLayout.setupWithViewPager(homeVp);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }
}
