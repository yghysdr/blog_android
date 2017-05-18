package com.shun.blog.ui.home.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.shun.blog.Jump;
import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_login_btn)
    Button homeLoginBtn;
    @BindView(R.id.home_fl)
    FrameLayout homeFl;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }


    @Override
    protected void init() {
        super.init();
        HomeFragment homeFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_fl, homeFragment);
        transaction.commit();
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
