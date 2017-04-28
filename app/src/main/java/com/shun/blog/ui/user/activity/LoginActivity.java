package com.shun.blog.ui.user.activity;

import com.shun.blog.R;
import com.shun.blog.base.mvp.BaseMvpActivity;
import com.shun.blog.ui.user.presenter.LoginPresenterImpl;

public class LoginActivity extends BaseMvpActivity<LoginPresenterImpl> {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }
}
