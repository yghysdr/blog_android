package com.github.yghysdr.http;

import io.reactivex.Observer;

/**
 * 统一处理错误
 */

public abstract class NetObserver<T> implements Observer<T> {


    @Override
    public void onError(Throwable e) {
        HttpException exception;
        exception = HttpConfiguration.getBuilder()
                .getNetExceptionHandler()
                .handleException(e);
        onError(exception);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onError(HttpException e);

}
