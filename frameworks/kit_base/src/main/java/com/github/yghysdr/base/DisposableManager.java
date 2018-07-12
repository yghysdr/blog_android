package com.github.yghysdr.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * RxJava订阅管理
 */

public class DisposableManager {
    private CompositeDisposable mCompositeDisposable;

    public DisposableManager() {

    }

    /**
     * Subscription subscribe = observable.subscribe(subscriber);
     * 被观察者订阅观察者会返回一个Subscription对象。
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
