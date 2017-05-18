package com.shun.blog.ui.article.view;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.article.presenter.NewPresenterImpl;


public class NewFragment extends BaseFragment<NewPresenterImpl> {

    public NewFragment() {
        // Required empty public constructor
        mReUse = true;
    }

    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new;
    }
}
