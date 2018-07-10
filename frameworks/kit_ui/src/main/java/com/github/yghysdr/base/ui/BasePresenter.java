package com.github.yghysdr.base.ui;

import io.reactivex.disposables.Disposable;

/**
 * Presenter 基础类
 */

public abstract class BasePresenter {
    protected DisposableManager mDisposableManager;

    public BasePresenter() {
        mDisposableManager = new DisposableManager();
    }

    public void attachView() {
    }

    public void detachView() {
        mDisposableManager.clear();
    }

    public void addSubscribe(Disposable disposable) {
        mDisposableManager.addDisposable(disposable);
    }

}
