package com.shun.blog.ui.user.contract;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.User;

import rx.Observable;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class LoginContract {
    public interface View {
        void loginSuccess(User user);

        void loginFailed();
    }

    public interface Presenter {
        void login(User user);
    }

    public interface Model {
        Observable<BaseResponse<User>> login(User user);
    }
}