package com.shun.blog.api.interceptor;

import android.text.TextUtils;

import com.shun.blog.BaseApplication;
import com.shun.blog.utils.NetUtil;
import com.socks.library.KLog;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yghysdr on 2016/10/12.
 * 请求网络的拦截器，设置缓存
 */

public class CacheInterceptor implements Interceptor {

    /**
     * [知识预备]
     * CacheControl类  的缓存策略
     * 1.  noCache  不使用缓存，全部走网络
     * 2.  noStore   不使用缓存，也不存储缓存
     * 3.  onlyIfCached 只使用缓存
     * 4.  maxAge  设置最大失效时间，失效则不使用 需要服务器配合
     * 5.  maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言
     * 6.  minFresh 设置有效时间，依旧如上
     * 7.  FORCE_NETWORK 只走网络
     * 8.  FORCE_CACHE 只走缓存
     * <p>
     * http请求\响应中的Cache-Control
     * <p>
     * 设置max-age=0
     * 请求结果缓存，但会立即过期，每次请求都会请求新的数据，但没网络时，可以读取到这个缓存。
     * 设置no-cache时，
     * 请求结果不缓存，每次请求都会请求新的数据，但没网络时，读取不到数据。
     */

    //无网络缓存时间
    private static final int CACHE_TIME_NO_NET = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
    //无网络缓存策略
    private static final String CACHE_CONTROL_NO_NET = "public, only-if-cached, max-stale=" + CACHE_TIME_NO_NET;

    //有网络缓存时间，这是统一设置缓存策略
    private static final int CACHE_TIME = 60 * 60; //有网络时 设置缓存超时时间1个小时,
    //缓存策略
    private static final String CACHE_CONTROL = "public, max-age=" + CACHE_TIME;
    /**
     * 不设置缓存
     */
    private static final String CACHE_CONTROL_NO_CACHE = "no-cache";//"max-age=0";

    /**
     * 有网络时，设置缓存策略。是否缓存，启用缓存时，用默认缓存时间一小时，
     * 在请求头设置发送缓存策略，响应体中解析
     *
     * @return
     */
    public static String getCacheControl(boolean cacheAble) {
        return cacheAble ? CACHE_CONTROL : CACHE_CONTROL_NO_CACHE;
    }

    /**
     * 有网络时，设置缓存策略。缓存时间是秒数
     * 设置0时，策略是max-age=0
     * 请求结果缓存，但会立即过期，每次请求都会请求新的数据，但没网络时，可以读取到这个缓存。
     * 设置no-cache时，
     * 请求结果不缓存，每次请求都会请求新的数据，但没网络时，读取不到数据。
     * <p>
     * 在请求头设置缓存策略，在响应
     *
     * @return
     */
    public static String getCacheControl(long sec) {
        return "max-age=" + String.valueOf(sec);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable(BaseApplication.instance)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            KLog.d("cache_url", "缓存读取");
        }

        Response response = chain.proceed(request);
        if (NetUtil.isNetworkAvailable(BaseApplication.instance)) {
            //获取请求头的缓存策略，如果没有设置使用默认策略
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = CACHE_CONTROL;
            }
            return response.newBuilder()
                    //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", cacheControl)//设置缓存超时时间
                    .removeHeader("Pragma")
                    .build();
        } else {
            return response.newBuilder()
                    .header("Cache-Control", CACHE_CONTROL_NO_NET)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
