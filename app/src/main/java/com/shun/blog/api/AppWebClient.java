package com.shun.blog.api;

import android.util.SparseArray;

import com.shun.blog.api.interceptor.CacheInterceptor;
import com.shun.blog.api.interceptor.HeaderInterceptor;
import com.shun.blog.api.interceptor.LogInterceptor;
import com.shun.blog.api.interceptor.QueryParInterceptor;
import com.shun.blog.app.Global;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yghysdr on 2016/9/30.
 */

public class AppWebClient {

    private Retrofit mRetrofit;
    private ApiStores mApiStores;
    private static final int CONN_TIME = 15;
    private static final int READ_TIME = 20;
    private static final int WRITE_TIME = 20;

    private static SparseArray<AppWebClient> mRetrofitManager
            = new SparseArray<>(HostType.TYPE_COUNT);

    public static ApiStores getApiStores(int type) {
        AppWebClient retrofitManager = mRetrofitManager.get(type);
        if (retrofitManager == null) {
            retrofitManager = new AppWebClient(type);
            mRetrofitManager.put(type, retrofitManager);
        }
        return retrofitManager.mApiStores;
    }

    private AppWebClient(int type) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //缓存
        builder.addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                //请求头
                .addInterceptor(new HeaderInterceptor())
                //请求体中的公共参数
                .addInterceptor(new QueryParInterceptor())
                //设置超时
                .connectTimeout(CONN_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS);
        if (Global.DEBUG) {
            builder.addInterceptor(new LogInterceptor());
        }
        //错误重连
//        builder.retryOnConnectionFailure(true);
        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HostType.getHost(type))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        mApiStores = mRetrofit.create(ApiStores.class);
    }
}
