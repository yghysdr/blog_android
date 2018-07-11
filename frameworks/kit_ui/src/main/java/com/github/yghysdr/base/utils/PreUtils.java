package com.github.yghysdr.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.github.yghysdr.base.BaseApp;

// 永久化简单参数的存储

public class PreUtils {

    public static final String CONFIG_FILE_NAME = "config";


    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long defValue) {
        SharedPreferences sp = BaseApp.getContext().getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static <T> T getObject(String key, Class<T> clazz) {
        String json = getString(key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> getListObject(String key, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        String json = getString(key, "");
        return new Gson().fromJson(json, type);
    }


    public static void putObject(String key, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(key, json);
    }

    static class ParameterizedTypeImpl implements ParameterizedType {

        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


}
