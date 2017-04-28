package com.shun.blog.base.mvp;

import com.shun.blog.baserx.RxManager;

import rx.Observable;
import rx.Subscriber;

/**
 * mvp的基类Presenter
 * 所有的Presenter继承BaseMvpPresenter，并实现定义一个接口实现自己特有等方法
 *
 * @param <V>
 */
public abstract class BaseMvpPresenter<V> {
    protected V mView;
    private RxManager mRxManage = new RxManager();

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

    /**
     * Subscription subscribe = observable.subscribe(subscriber);
     * 异步操作一定要放在这，防止view为null是对view操作
     *
     * @param observable
     * @param subscriber
     * @param <R>        要解析的数据类型
     */
    protected <R> void addAsync(Observable<R> observable, Subscriber<R> subscriber) {
        mRxManage.addWebRequestSubscription(observable, subscriber);
    }

}
