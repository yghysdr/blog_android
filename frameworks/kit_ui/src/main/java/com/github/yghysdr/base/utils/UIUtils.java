package com.github.yghysdr.base.utils;

import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Toast;

import com.github.yghysdr.base.BaseApp;


public class UIUtils {

    public static Toast mToast;

    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.getContext(), "", duration);
        }
        mToast.setText(msg);
        mToast.show();
    }


    /**
     * dip-->px
     */
    public static int dip2Px(int dip) {
        float density = BaseApp.getRes().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2dip(int px) {
        float density = BaseApp.getRes().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    /**
     * sp-->px
     */
    public static int sp2px(int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, BaseApp.getRes().getDisplayMetrics()) + 0.5f);
    }

    //判断是否是快速点击
    private static long lastClickTime = 0L; //上一次点击的时间

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD < 1000) {
            //写自己的双击逻辑
            return true;
        }
        lastClickTime = time;
        return false;
    }
}