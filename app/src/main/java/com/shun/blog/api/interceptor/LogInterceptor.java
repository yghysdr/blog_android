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
        KLog.d("http_request",
                String.format("%s %s %n headers: %n %s body:%n %s",
                        request.method(),
                        request.url(),
                        request.headers(),
                        request.body())

        );
        //chain.proceed(request) 是每个拦截器的关键部分的实现
        Response response = chain.proceed(request);
        KLog.d("http_response",
                String.format("%s %n %s",
                        response.request().url(),
                        response.headers())
        );
        return response;
    }
}
