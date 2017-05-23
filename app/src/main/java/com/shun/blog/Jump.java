package com.shun.blog;

import android.content.Intent;

import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.article.view.ArticleActivity;
import com.shun.blog.ui.user.view.LoginActivity;
import com.shun.blog.utils.UserData;

/**
 * Created by yghysdr on 2017/4/28.
 */

public class Jump {

    public static void login(BaseActivity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public static void edit(BaseActivity activity) {
        if (UserData.getCurrentUser() == null) {
            activity.startActivity(new Intent(activity, LoginActivity.class));
        } else {
            activity.startActivity(new Intent(activity, ArticleActivity.class));
        }
    }
}
