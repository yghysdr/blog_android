package com.shun.blog.base.net;

import rx.Subscriber;

/**
 * 统一处理错误
 */

public abstract class NetSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    protected abstract void onSuccess(T t);

    protected abstract void onError(Exception e);


}
