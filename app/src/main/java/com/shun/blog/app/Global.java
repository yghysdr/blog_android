package com.shun.blog.app;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by yghysdr on 16/11/29.
 * 全局参数。
 */

public class Global {
    public static boolean DEBUG = true;

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }


    private static final ThreadLocal<Gson> reuseGson = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    /**
     * 获取Gson对象
     *
     * @return
     */
    public static Gson getGson() {
        return reuseGson.get();
    }

}
