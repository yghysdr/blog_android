package com.shun.blog.api.interceptor;

import com.socks.library.KLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yghysdr on 2016/10/11.
 * 请求日志拦截器
 */
public class LogInterceptor implements Interceptor {//拦截者； 插入器


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        KLog.d("request_url", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        //chain.proceed(request) 是每个拦截器的关键部分的实现
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        KLog.d("response_url", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
