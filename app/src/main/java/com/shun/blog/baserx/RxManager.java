package com.shun.blog.baserx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yghsdr on 2016/10/14.
 * 方便RxJava相关代码的生命周期处理
 */

public class RxManager {
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * Subscription subscribe = observable.subscribe(subscriber);
     * 被观察者订阅观察者会返回一个Subscription对象。
     */
    public void addAsync(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消所有订阅
    }
}
