package com.github.yghysdr.http;

import io.reactivex.SingleObserver;

/**
 * 统一处理错误
 */

public abstract class NetSingleObserver<T> implements SingleObserver<T> {


    @Override
    public void onError(Throwable e) {
        HttpException exception;
        exception = HttpConfiguration.getBuilder()
                .getNetExceptionHandler()
                .handleException(e);
        onError(exception);
    }


    protected abstract void onError(HttpException e);

}
