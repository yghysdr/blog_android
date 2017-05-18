package com.shun.blog.base.ui;

import android.content.Context;

import com.shun.blog.base.rx.RxManager;
import com.shun.blog.utils.TUtil;

/**
 * mvp的基类Presenter
 * 所有的Presenter继承BaseMvpPresenter，并实现定义一个接口实现自己特有等方法
 *
 * @param <V>
 */
public abstract class BasePresenter<V, M extends BaseModel> {
    protected V mView;
    protected M mMode;
    protected Context mContext;
    protected RxManager mRxManage = new RxManager();

    /**
     * MvpBasePresenter有一个MvpBaseActivity
     *
     * @param mvpView
     */
    protected void attachView(V mvpView) {
        this.mView = mvpView;
        mMode = TUtil.getT(this, 1);
    }

    protected void detachView() {
        this.mView = null;
        mRxManage.clear();
    }

}
