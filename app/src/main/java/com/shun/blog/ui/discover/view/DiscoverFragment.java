package com.shun.blog.ui.discover.view;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.discover.presenter.DiscoverPresenterImpl;

public class DiscoverFragment extends BaseFragment<DiscoverPresenterImpl> {

    public DiscoverFragment() {
        mReUse = false;
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }
}
