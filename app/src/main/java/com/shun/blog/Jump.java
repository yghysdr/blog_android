package com.shun.blog;

import android.content.Intent;

import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.user.view.LoginActivity;

/**
 * Created by yghysdr on 2017/4/28.
 */

public class Jump {

    public static void login(BaseActivity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }
}
