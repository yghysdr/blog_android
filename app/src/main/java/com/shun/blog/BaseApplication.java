package com.shun.blog;

import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;

import com.shun.blog.app.Global;
import com.shun.blog.utils.UserData;
import com.socks.library.KLog;


/**
 * Created by yghysdr on 16/11/26.
 */

public class BaseApplication extends MultiDexApplication {
    public static BaseApplication instance;

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
        Global.init(this);
        UserData.init(this);
        KLog.init(Global.DEBUG);
    }

}
