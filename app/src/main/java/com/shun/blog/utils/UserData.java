package com.shun.blog.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shun.blog.app.Global;
import com.shun.blog.bean.User;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by yghysdr on 16/11/29.
 * 现在只保存单用户信息。
 * 多用户扩展需要添加手机号作为唯一标示
 */

public class UserData {

    public static User mCurrentUser;
    public static Context mContext;

    private static final String USER_INFO = "user_info";
    private static final String EXIT_USER_PHONE = "exit_user_key";
    private static final String SEARCH_HISTORY_ARR = "search_history";

    public static void init(Context context) {
        mContext = context;
    }

    public static String exitUser() {
        return (String) SPUtils.get(mContext, EXIT_USER_PHONE, "");
    }

    /**
     * 保存token
     *
     * @param token
     */
    public static void saveToken(String token) {
        User userModel = getCurrentUser();
        userModel.token = token;
        saveCurrentUser(userModel);
    }

    public static String getToken() {
        if (getCurrentUser() == null) {
            return "";
        }
        return getCurrentUser().token;
    }


    /**
     * 保存当前用户
     *
     * @param userModel
     */
    public static void saveCurrentUser(User userModel) {
        Gson gson = Global.getGson();
        String profile = gson.toJson(userModel);
        SPUtils.put(mContext, USER_INFO, profile);
        mCurrentUser = userModel;
        SPUtils.put(mContext, EXIT_USER_PHONE, userModel.phone);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static User getCurrentUser() {
        if (mCurrentUser == null) {
            String profile = (String) SPUtils.get(mContext, USER_INFO, "");
            if (!TextUtils.isEmpty(profile)) {
                Gson gson = Global.getGson();
                mCurrentUser = gson.fromJson(profile, User.class);
            }
        }
        return mCurrentUser;
    }

    /**
     * 删除用户
     */
    public static void deleteCurrentUser() {
        User currentUserModel = getCurrentUser();
        if (currentUserModel != null) {
            SPUtils.put(mContext, EXIT_USER_PHONE, currentUserModel.phone);
        }
        SPUtils.remove(mContext, USER_INFO);
        mCurrentUser = null;
    }

    /**
     * 存储搜索历史数据
     */

    public static void saveSearchHistoryArray(ArrayList<String> stringsArray) {
        JSONArray jsonArray = new JSONArray();
        for (String b : stringsArray) {
            jsonArray.put(b);
        }
        SPUtils.put(mContext, SEARCH_HISTORY_ARR, jsonArray.toString());
    }

    /**
     * 获取搜索历史数据
     */
    public static ArrayList<String> getSearchHistoryArray(Context context) {
        String searchDate = (String) SPUtils.get(context, "", "[]");
        ArrayList<String> resArray = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(searchDate);
            for (int i = 0; i < jsonArray.length(); i++) {
                resArray.add(i, jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resArray;
    }
}
