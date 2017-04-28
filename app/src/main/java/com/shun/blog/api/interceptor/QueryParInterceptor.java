package com.shun.blog.api.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yghysdr on 2016/10/12.
 * 设置一些公共参数，这些参数是指？之后的
 */
public class QueryParInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
//                .addQueryParameter("platform", "android")
//                .addQueryParameter("version", "1.0.0")
                .build();
        Request request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
