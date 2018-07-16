package io.yghysdr.login;

import android.content.Context;

import com.github.yghysdr.http.HttpException;

import io.blog.res.BaseResponse;
import io.blog.res.bean.User;
import io.reactivex.Observable;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class LoginContract {
    public interface View {
        void loginSuccess(User user);

        void loginFailed(HttpException exception);
    }

    public interface Presenter {
        void login(View view, User user, Context context);
    }

    public interface Model {
        Observable<BaseResponse<User>> login(User user);
    }
}