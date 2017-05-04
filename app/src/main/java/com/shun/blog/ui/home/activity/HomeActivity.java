package com.shun.blog.ui.home.activity;

import android.view.View;
import android.widget.Button;

import com.shun.blog.R;
import com.shun.blog.base.mvp.BaseMvpActivity;
import com.shun.blog.ui.home.model.HomeModelImpl;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;
import com.shun.blog.Jump;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseMvpActivity<HomePresenterImpl, HomeModelImpl> {

    @BindView(R.id.home_login_btn)
    Button homeLoginBtn;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }


    @OnClick({R.id.home_login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_login_btn:
                Jump.login(this);
                break;
        }
    }
}
