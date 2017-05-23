package com.shun.blog.base.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import com.shun.blog.BaseApplication;
import com.shun.blog.R;
import com.shun.blog.app.AppConstants;
import com.shun.blog.app.AppManager;
import com.shun.blog.utils.SPUtils;
import com.shun.blog.utils.StatusBarUtil;
import com.shun.blog.utils.TUtil;

import butterknife.ButterKnife;

/**
 * Created by yghysdr on 16/11/26.
 * 所有Activity共同的
 * 如果是MVP模式，只需要实现泛型P即可
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected BaseApplication mBaseApplication;
    protected Context mBaseActivity;
    protected P mPresenter;
    protected boolean mFinishAnim = true, mStartAnim = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        doBeforeSetContentView();
        super.onCreate(savedInstanceState);
        if ((Boolean) SPUtils.get(this, AppConstants.THEME_NORMAL, true)) {
            initStatusBar(R.color.colorPrimary);
            initTheme(R.style.AppTheme);
        } else {
            initStatusBar(R.color.colorPrimary_n);
            initTheme(R.style.AppThemeNight);
        }
        setContentView(getLayoutResource());
        //设置只能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mBaseApplication = BaseApplication.instance;
        mBaseActivity = this;
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.mContext = this;
        }
        ButterKnife.bind(this);
        init();
    }

    private void initTheme(@StyleRes int resid) {
        setTheme(resid);
    }

    protected void init() {
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {
    }

    /**
     * 当不需要的是否覆盖即可
     */
    public void initStatusBar(@ColorRes int id) {
        StatusBarUtil.setColor(this, mBaseApplication.getResColor(id), 0);
    }

    public abstract int getLayoutResource();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (mStartAnim) {
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (mFinishAnim) {
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        }
    }
}
