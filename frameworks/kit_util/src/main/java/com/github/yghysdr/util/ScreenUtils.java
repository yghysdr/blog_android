/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.github.yghysdr.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 屏幕，单位
 */
public class ScreenUtils {

    private static int mScreenH = 0;

    public static int getScreenW(Context c) {
        if (mScreenH != 0) {
            return mScreenH;
        }
        mScreenH = c.getResources().getDisplayMetrics().widthPixels;
        return mScreenH;
    }

    private static int mScreenW = 0;

    public static int getScreenH(Context c) {

        if (mScreenW != 0) {
            return mScreenW;
        }
        mScreenW = c.getResources().getDisplayMetrics().heightPixels;
        return mScreenW;
    }

    private static int mScreenRealH = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenRealH(Context context) {
        if (mScreenRealH != 0) {
            return mScreenRealH;
        }
        int h;
        WindowManager winMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = winMgr.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(dm);
            h = dm.heightPixels;
        } else {
            try {
                Method method = Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, dm);
                h = dm.heightPixels;
            } catch (Exception e) {
                display.getMetrics(dm);
                h = dm.heightPixels;
            }
        }
        mScreenRealH = h;
        return h;
    }


    private static int mStatueBarH = 0;

    public static int getStatusBarH(Context context) {
        if (mStatueBarH != 0) {
            return mStatueBarH;
        }
        Class<?> c;
        Object obj;
        Field field;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        mStatueBarH = statusBarHeight;
        return mStatueBarH;
    }

    private static int mNavigationBarH = 0;

    public static int getNavigationBarrH(Context c) {
        if (mNavigationBarH != 0) {
            return mNavigationBarH;
        }
        Resources resources = c.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        mNavigationBarH = resources.getDimensionPixelOffset(identifier);
        return mNavigationBarH;
    }

    /**
     * 心事隐藏状态栏
     *
     * @param enable
     * @param activity
     */
    public static void full(boolean enable, Activity activity) {
        if (enable) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
