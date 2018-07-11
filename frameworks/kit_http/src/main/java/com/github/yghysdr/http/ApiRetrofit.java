package com.github.yghysdr.http;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * ApiRetrofit
 */

public class ApiRetrofit {
    private final Retrofit mRetrofit;
    private OkHttpClient mClient;
    private static HttpConfiguration.Builder mBuilder = HttpConfiguration.getBuilder();

    private static HashMap<String, ApiRetrofit> apiRetrofitManagers = new HashMap<>();

    public ApiRetrofit(String url) {

        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            mClientBuilder.networkInterceptors().add(new StethoInterceptor());
        }
        if (mBuilder.getAuthenticator() != null) {
            mClientBuilder.authenticator(mBuilder.getAuthenticator());
        }
        mClientBuilder.connectTimeout(mBuilder.getConnectTimeout(), TimeUnit.MILLISECONDS);
        mClientBuilder.retryOnConnectionFailure(mBuilder.isRetryOnConnectionFailure());
        if (mBuilder.getInterceptor() != null) {
            mClientBuilder.addInterceptor(mBuilder.getInterceptor());
        }
        mClientBuilder.retryOnConnectionFailure(true);
        mClient = mClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }


    public static ApiRetrofit getInstance(String url) {
        ApiRetrofit mApiRetrofit = apiRetrofitManagers.get(url);
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit(url);
                    apiRetrofitManagers.put(url, mApiRetrofit);
                }
            }
        }
        return mApiRetrofit;
    }

    public static ApiRetrofit getInstance(int type) {
        return getInstance(HttpConfiguration.getBuilder().getHost().getBaseServerUrl(type));
    }

    public static ApiRetrofit getInstance() {
        return getInstance(0);
    }

}
