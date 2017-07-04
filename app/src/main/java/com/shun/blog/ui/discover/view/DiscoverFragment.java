package com.shun.blog.ui.discover.view;

import android.widget.FrameLayout;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.discover.contract.DiscoverContract;
import com.shun.blog.ui.discover.presenter.DiscoverPresenterImpl;
import com.shun.blog.utils.ThemeUtil;

import butterknife.BindView;

public class DiscoverFragment extends BaseFragment<DiscoverPresenterImpl>
        implements DiscoverContract.View {

    @BindView(R.id.discover_fl)
    FrameLayout discoverFl;

    public DiscoverFragment() {
        mReUse = true;
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    public void beforeReturn() {
        super.beforeReturn();
        initToolBar(getString(R.string.title_discover));
    }

    @Override
    public void refreshTheme() {
        refreshToolbar();
        discoverFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
    }
}
