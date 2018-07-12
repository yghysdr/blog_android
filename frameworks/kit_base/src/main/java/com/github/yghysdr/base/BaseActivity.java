package com.github.yghysdr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Activity基础类
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;
    private PresenterManager mPresenterManager;
    private DisposableManager mDisposableManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        mDisposableManager = new DisposableManager();
        mPresenter = createPresenter();
        mPresenterManager = new PresenterManager();
        if (mPresenter != null) {
            mPresenterManager.mPresenterList.add(mPresenter);
        }
        addPresenter(mPresenterManager.mPresenterList);
        mPresenterManager.attach();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setBarStatus();
    }

    protected void setBarStatus() {

    }

    /**
     * 单个Presenter
     *
     * @return
     */
    protected T createPresenter() {
        return null;
    }

    /**
     * 多个Presenter 创建presenter之后将它add到basePresenters
     *
     * @param basePresenters
     */
    public void addPresenter(List<BasePresenter> basePresenters) {

    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    //初始化view
    protected void initView() {
    }

    public void initData() {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenterManager.detach();
        mDisposableManager.clear();
    }


    public void addDisposable(Disposable disposable) {
        mDisposableManager.addDisposable(disposable);
    }
}
