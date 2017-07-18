package com.shun.blog.ui.main.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shun.blog.R;
import com.shun.blog.api.ApiStores;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.article.view.ArticleListFragment;
import com.shun.blog.ui.home.view.ArchiveListFragment;
import com.shun.blog.ui.home.view.TagFragment;
import com.shun.blog.ui.main.contract.IndexContract;
import com.shun.blog.ui.main.presenter.IndexPagerAdapter;
import com.shun.blog.ui.main.presenter.IndexPresenterImpl;
import com.shun.blog.utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IndexFragment extends BaseFragment<IndexPresenterImpl> implements
        IndexContract.View {

    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.home_tl)
    TabLayout homeTL;

    public IndexFragment() {
        // Required empty public constructor
        mReUse = true;
    }

    public static IndexFragment newInstance() {
        return new IndexFragment();
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
        IndexPagerAdapter adapter = new IndexPagerAdapter(getFragmentManager(), fragmentList);
        homeVp.setAdapter(adapter);
        homeTL.setupWithViewPager(homeVp);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    public void refreshTheme() {
        homeTL.setTabTextColors(
                ThemeUtil.getColorId(ThemeUtil.txtNavOff),
                ThemeUtil.getColorId(ThemeUtil.txtNavOn));
        homeTL.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
    }
}
