package com.shun.blog.ui.home.activity;

import com.shun.blog.R;
import com.shun.blog.base.mvp.BaseMvpActivity;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;

public class HomeActivity extends BaseMvpActivity<HomePresenterImpl> {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
