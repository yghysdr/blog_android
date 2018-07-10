package io.yghysdr.login;

import android.content.Context;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.User;
import io.reactivex.Observable;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class LoginContract {
    public interface View {
        void loginSuccess(User user);

        void loginFailed();
    }

    public interface Presenter {
        void login(View view, User user, Context context);
    }

    public interface Model {
        Observable<BaseResponse<User>> login(User user);
    }
}