package io.yghysdr.login;

import com.github.yghysdr.http.ApiRetrofit;
import io.blog.res.BaseResponse;
import io.blog.res.bean.User;
import io.reactivex.Observable;

/**
 * Created by yghysdr on 2017/04/27
 */

public class LoginModelImp implements LoginContract.Model {

    @Override
    public Observable<BaseResponse<User>> login(User user) {
        return ApiRetrofit
                .getInstance()
                .getRetrofit()
                .create(ILoginApi.class)
                .login(user);
    }
}