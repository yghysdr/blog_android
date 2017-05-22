package com.shun.blog.base;

import android.app.Application;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class BaseUtils {
    protected static Application mApplication;
    public static void init(Application application){
        mApplication = application;
    }
}
