package com.shun.blog.base.ui;

import android.app.Activity;

import com.shun.blog.base.rx.RxManager;

/**
 * mvp的基类Presenter
 * 所有的Presenter继承BaseMvpPresenter，并实现定义一个接口实现自己特有等方法
 *
 * @param <V>
 */
public abstract class BaseMvpPresenter<V, M> {
    protected V mView;
    protected M mMode;
    protected Activity mContext;
    protected RxManager mRxManage = new RxManager();

    /**
     * MvpBasePresenter有一个MvpBaseActivity
     *
     * @param mvpView
     */
    protected void attachView(V mvpView) {
        this.mView = mvpView;
    }

    protected void detachView() {
        this.mView = null;
        mRxManage.clear();
    }

}
