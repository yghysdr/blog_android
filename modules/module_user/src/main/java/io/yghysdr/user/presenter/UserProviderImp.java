package io.yghysdr.user.presenter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.blog.res.bean.User;

import com.github.yghysdr.base.BaseApp;
import io.yghysdr.blog.wrap.json.JsonHelper;
import com.github.yghysdr.util.PreUtils;

import io.yghysdr.mediator.user.IContentUser;
import io.yghysdr.mediator.user.IUserProvider;

@Route(path = IContentUser.USER_SERVICE_USER_INFO)
public class UserProviderImp implements IUserProvider {

    private static final String USER = "login.user.info";

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
            mPostUser = JsonHelper.fromJson(PreUtils.getString(BaseApp.getContext(), USER, ""), User.class);
        }
        return mPostUser;
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            mPostUser = user;
            PreUtils.putString(BaseApp.getContext(), USER, JsonHelper.toJson(user));
        }
    }

    @Override
    public void clearUser() {
        mPostUser = null;
        PreUtils.putString(BaseApp.getContext(), USER, "");
    }


    @Override
    public void init(Context context) {

    }
}
