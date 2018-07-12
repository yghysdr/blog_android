package io.yghysdr.main.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.github.yghysdr.base.BaseFragment;

import com.github.yghysdr.theme.ThemeUtil;

import io.yghysdr.main.R;
import io.yghysdr.main.R2;
import io.yghysdr.main.contract.IndexContract;
import io.yghysdr.main.presenter.IndexPagerAdapter;
import io.yghysdr.main.presenter.IndexPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.yghysdr.mediator.article.MediatorArticle;

public class IndexFragment extends BaseFragment implements
        IndexContract.View {

    @BindView(R2.id.home_vp)
    ViewPager homeVp;
    @BindView(R2.id.home_tl)
    TabLayout homeTL;
    private IndexPresenterImpl indexPresenter;

    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected void LazyLoad() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MediatorArticle.getArticleListFragment());
        fragmentList.add(MediatorArticle.getArchiveListFragment());
        fragmentList.add(MediatorArticle.getTagFragment());
        IndexPagerAdapter adapter = new IndexPagerAdapter(getFragmentManager(), fragmentList);
        homeVp.setOffscreenPageLimit(fragmentList.size());
        homeVp.setAdapter(adapter);
        homeTL.setupWithViewPager(homeVp);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.main_fragment_index;
    }


    @Override
    public void addPresenter(List basePresenters) {
        super.addPresenter(basePresenters);
        indexPresenter = new IndexPresenterImpl();
        basePresenters.add(indexPresenter);
    }


    @Override
    public void refreshTheme() {
        homeTL.setTabTextColors(
                ThemeUtil.getColorId(getContext(), ThemeUtil.txtNavOff),
                ThemeUtil.getColorId(getContext(), ThemeUtil.txtNavOn));
        homeTL.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
    }

}
