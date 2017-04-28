package com.shun.blog.utils;

import android.content.Context;


/**
 * Created by yghysdr on 2016/12/28.
 */

public class AssetsUtils {
    public static String readText(Context context, String assetPath) {
        try {
            return ReadFileUtils.getJson(context, assetPath);
        } catch (Exception e) {
            return "";
        }
    }
}
