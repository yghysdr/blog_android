package com.shun.blog.baserx;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HX on 2016/10/14.
 * 方便RxJava相关代码的生命周期处理
 */

public class RxManager {
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * Subscription subscribe = observable.subscribe(subscriber);
     * 被观察者订阅观察者会返回一个Subscription对象。
     *
     * @param observable
     * @param subscriber
     * @param <R>        要解析的数据类型
     */
    public <R> void addWebRequestSubscription(Observable<R> observable,
                                              Subscriber<R> subscriber) {
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消所有订阅
    }
}
