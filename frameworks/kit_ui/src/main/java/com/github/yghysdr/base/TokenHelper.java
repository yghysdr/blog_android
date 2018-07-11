package com.github.yghysdr.base;

import android.text.TextUtils;

import com.github.yghysdr.base.utils.PreUtils;

public class TokenHelper {

    private static final String TOKEN = "com.github.yghysdr.base.TokenHelper.token";
    private static final String ID = "com.github.yghysdr.base.TokenHelper.id";

    private static String mToken = "";
    private static String mId = "";

    public static void init() {
        mToken = getToken();
        mId = getID();
    }

    public static void clear() {
        saveID("");
        saveToken("");
        mToken = "";
        mId = "";
    }

    public static String getID() {
        mId = !TextUtils.isEmpty(mId) ? mId : PreUtils.getString(ID, "");
        return mId;
    }

    public static String getToken() {
        mToken = !TextUtils.isEmpty(mToken) ? mToken : PreUtils.getString(TOKEN, "");
        return mToken;
    }

    public static void saveToken(String token) {
        mToken = token;
        PreUtils.putString(TOKEN, token);
    }

    public static void saveID(String userId) {
        mId = userId;
        PreUtils.putString(ID, userId);
    }


}
