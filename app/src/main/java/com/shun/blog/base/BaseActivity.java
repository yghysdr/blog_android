package com.shun.blog.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shun.blog.BaseApplication;
import com.shun.blog.R;
import com.shun.blog.app.AppManager;
import com.shun.blog.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by yghysdr on 16/11/26.
 * 所有Activity共同的
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected BaseApplication mBaseApplication;
    protected Context mBaseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        doBeforeSetContentView();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        //设置只能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mBaseApplication = BaseApplication.instance;
        mBaseActivity = this;
        ButterKnife.bind(this);
        initStatusBar();
        init();
    }

    private void init() {
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {
    }

    /**
     * 当不需要的是否覆盖即可
     */
    public void initStatusBar() {
        StatusBarUtil.setColor(this, mBaseApplication.getResColor(R.color.colorPrimary), 0);
    }

    public abstract int getLayoutResource();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
