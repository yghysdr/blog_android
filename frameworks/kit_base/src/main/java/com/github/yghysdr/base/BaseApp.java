package com.github.yghysdr.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import com.github.yghysdr.util.ClassUtils;


/**
 * Application基础类
 */

public class BaseApp extends MultiDexApplication {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    public MyActivitiesLife activitiesLife; //生命周期管理
    private List<IApplicationDelegate> mAppDelegateList;

    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值
        mContext = getApplicationContext();
        afterOnCreate();
        String progress = getProcessName(this);
        if (progress != null) {
            if (getPackageName().equals(progress)) {
                initLibrary();
                mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, getPackageName());
                for (IApplicationDelegate delegate : mAppDelegateList) {
                    delegate.onCreate(this);
                }
            }
        }
    }

    protected void afterOnCreate() {

    }

    private void initLibrary() {
        initARouter();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        if (activitiesLife == null) {
            activitiesLife = new MyActivitiesLife();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        BaseApp.mContext = mContext;
    }

    public static int getResColor(int colorId) {
        return getRes().getColor(colorId);
    }

    public static Drawable getResDra(int drawableId) {
        return getRes().getDrawable(drawableId);
    }

    public static String getResStr(int strId) {
        return getRes().getString(strId);
    }

    public static float getDime(@DimenRes int id) {
        return getRes().getDimension(id);
    }

    public static Resources getRes() {
        return getContext().getResources();
    }

    private static int resumed;
    private static int paused;

    class MyActivitiesLife implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
            ++resumed;
        }

        @Override
        public void onActivityPaused(Activity activity) {
            ++paused;
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

    }

    /**
     * 判断应用是否在前台
     *
     * @return
     */
    public static boolean isAppForeground() {
        return resumed > paused;
    }


    private String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}
