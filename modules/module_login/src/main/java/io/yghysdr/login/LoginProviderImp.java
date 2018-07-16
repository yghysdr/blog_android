package io.yghysdr.login;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yghysdr.blog.common.TokenHelper;
import io.yghysdr.mediator.login.IConstantLogin;
import io.yghysdr.mediator.login.ILoginProvider;
import io.yghysdr.mediator.user.MediatorUser;

/**
 * 用户信息
 *
 * @description 登录helper类
 */
@Route(path = IConstantLogin.LOGIN_SERVICE_USER)
public class LoginProviderImp implements ILoginProvider {

    @Override
    public void exitLogin() {
        TokenHelper.clear();
    }

    @Override
    public boolean isLogin() {
        return !TextUtils.isEmpty(TokenHelper.getToken())
                && !TextUtils.isEmpty(TokenHelper.getID())
                && MediatorUser.getUserProvider().getUser() != null;
    }


    @Override
    public void init(Context context) {

    }
}
