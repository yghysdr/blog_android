package io.yghysdr.blog.common;

import android.text.TextUtils;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.util.PreUtils;

public class TokenHelper {

    private static final String TOKEN = "io.yghysdr.blog.common.TokenHelper.token";
    private static final String ID = "io.yghysdr.blog.common.TokenHelper.id";

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
        mId = !TextUtils.isEmpty(mId) ? mId : PreUtils.getString(BaseApp.getContext(),ID, "");
        return mId;
    }

    public static String getToken() {
        mToken = !TextUtils.isEmpty(mToken) ? mToken : PreUtils.getString(BaseApp.getContext(),TOKEN, "");
        return mToken;
    }

    public static void saveToken(String token) {
        mToken = token;
        PreUtils.putString(BaseApp.getContext(),TOKEN, token);
    }

    public static void saveID(String userId) {
        mId = userId;
        PreUtils.putString(BaseApp.getContext(),ID, userId);
    }


}
