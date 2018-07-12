package com.github.yghysdr.util;

import android.content.Context;
import android.content.SharedPreferences;


// 永久化简单参数的存储

public class PreUtils {

    public static final String CONFIG_FILE_NAME = "config";


    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

}
