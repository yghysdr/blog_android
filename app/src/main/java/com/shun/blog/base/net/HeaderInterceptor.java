package com.shun.blog.base.net;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yghysdr on 2016/10/12.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request build = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("platform", "android")
                .addHeader("version", Build.VERSION.RELEASE + Build.MODEL)
                .build();
        return chain.proceed(build);
    }
}
