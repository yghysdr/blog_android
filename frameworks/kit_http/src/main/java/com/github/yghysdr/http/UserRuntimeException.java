package com.github.yghysdr.http;

/**
 * Created by yghysdr on 2018/3/23.
 * 用户自定义的运行时异常，在rx处理异常的时候可以被捕获，而系统抛出的运行时异常(非网络)不捕获
 */

public class UserRuntimeException extends RuntimeException{

    public UserRuntimeException(String message) {
        super(message);
    }
}

