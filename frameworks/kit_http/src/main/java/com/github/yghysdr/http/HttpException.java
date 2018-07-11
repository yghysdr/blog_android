package com.github.yghysdr.http;

/**
 * Created by yghysdr on 2017/11/9.
 */

public class HttpException extends Exception {
    public int code;
    public String message;

    public HttpException(Throwable throwable) {
        super(throwable);
    }
}