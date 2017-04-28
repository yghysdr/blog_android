package com.shun.blog.utils;

import android.widget.Toast;

import com.shun.blog.base.BaseUtils;


/**
 * Created by yghysdr on 16/11/27.
 */

public class ToastUtils extends BaseUtils {

    public static void showToast(int resId) {
        Toast.makeText(mApplication, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showToast(String msg) {
        Toast.makeText(mApplication, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String msg, int position) {
        Toast toast = Toast.makeText(mApplication, msg, Toast.LENGTH_SHORT);
        toast.setGravity(position, 0, 0);
        toast.show();
    }


    private static long firstClickTime = (long) 0;
    /**
     * 默认的允许显示消息的时间间隔
     */
    private static long timeInterval = 4000;

    /**
     * 限制时间
     *
     * @param msg
     */
    public static void showToastLimitTime(String msg) {
        if (System.currentTimeMillis() - firstClickTime > timeInterval) {
            showToast(msg);
            firstClickTime = System.currentTimeMillis();
            return;
        }
    }

    public static void showToastLimitTime(String msg, int positon) {
        if (System.currentTimeMillis() - firstClickTime > timeInterval) {
            showToast(msg, positon);
            firstClickTime = System.currentTimeMillis();
            return;
        }
    }
}
