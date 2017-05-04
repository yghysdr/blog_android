package com.shun.blog;

import android.app.Activity;
import android.content.Intent;

import com.shun.blog.ui.user.activity.LoginActivity;

/**
 * Created by yghysdr on 2017/4/28.
 */

public class Jump {

    public static void login(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }
}
