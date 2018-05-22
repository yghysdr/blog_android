package com.shun.blog;

import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;

import com.shun.blog.app.Global;
import com.shun.blog.base.BaseUtils;
import com.shun.blog.utils.ShareUtils;
import com.shun.blog.utils.UserData;
import com.shun.blog.weights.multistatelayout.MultiStateConfiguration;
import com.shun.blog.weights.multistatelayout.MultiStateLayout;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by yghysdr on 16/11/26.
 */

public class App extends MultiDexApplication {
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    public static int getResColor(int colorRes) {
        return instance.getResources().getColor(colorRes);
    }

    public static String getResString(int strRes) {
        return instance.getResources().getString(strRes);
    }

    public static float getResDimen(int strRes) {
        return instance.getResources().getDimension(strRes);
    }

    private void init() {
        LeakCanary.install(this);
        UserData.init(this);
        KLog.init(BuildConfig.DEBUG);
        BaseUtils.init(this);
        ShareUtils.init(this);
        MultiStateConfiguration.Builder builder = new MultiStateConfiguration.Builder();
        builder.setCommonEmptyLayout(R.layout.layout_empty)
                .setCommonNetworkErrorLayout(R.layout.layout_error_net)
                .setCommonErrorLayout(R.layout.layout_error)
                .setCommonLoadingLayout(R.layout.layout_loading);
        MultiStateLayout.setConfiguration(builder);
    }

}
