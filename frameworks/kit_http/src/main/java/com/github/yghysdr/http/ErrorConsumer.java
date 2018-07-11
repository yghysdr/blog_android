package com.github.yghysdr.http;


import io.reactivex.functions.Consumer;

public abstract class ErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) {
        HttpException httpException = HttpConfiguration.getBuilder()
                .getNetExceptionHandler()
                .handleException(throwable);
        onError(httpException);
    }

    public abstract void onError(HttpException e);
}
