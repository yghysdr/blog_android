package io.yghysdr.user.presenter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.blog.modle.bean.User;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.utils.PreUtils;

import io.yghysdr.mediator.user.IContentUser;
import io.yghysdr.mediator.user.IUserProvider;

@Route(path = IContentUser.USER_SERVICE_USER_INFO)
public class UserProviderImp implements IUserProvider {

    private static final String USER = "login.LoginProviderImp.user";

    private static User mPostUser;


    private static UserProviderImp mInstance;

    public static UserProviderImp getInstance() {
        if (mInstance == null) {
            mInstance = new UserProviderImp();
        }
        return mInstance;
    }

    @Override
    public User getUser() {
        if (mPostUser == null) {
            mPostUser = PreUtils.getObject(USER, User.class);
        }
        return mPostUser;
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            mPostUser = user;
            PreUtils.putObject(USER, mPostUser);
        }
    }

    @Override
    public void clearUser() {
        mPostUser = null;
        PreUtils.putObject(USER, "");
    }


    @Override
    public void init(Context context) {

    }
}
