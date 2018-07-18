package io.yghysdr.login;

import android.content.Context;

import com.github.yghysdr.http.ApiRetrofit;
import com.github.yghysdr.http.DialogObserver;
import com.github.yghysdr.http.HttpException;

import io.blog.res.BaseResponse;
import io.blog.res.bean.User;

import com.github.yghysdr.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.yghysdr.blog.common.TokenHelper;
import io.yghysdr.mediator.user.MediatorUser;

/**
 * Created by yghysdr on 2017/04/27
 */


public class LoginPresenterImp extends BasePresenter
        implements LoginContract.Presenter {


    @Override
    public void login(LoginContract.View view, User user, Context context) {
        ApiRetrofit.getInstance()
                .getRetrofit()
                .create(ILoginApi.class)
                .login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(userBaseResponse -> {
                    TokenHelper.saveID(userBaseResponse.data.uid + "");
                    TokenHelper.saveToken(userBaseResponse.data.token);
                    MediatorUser.getUserProvider().updateUser(userBaseResponse.data);
                })
                .subscribe(new DialogObserver<BaseResponse<User>>(context) {
                    @Override
                    public void onNext(BaseResponse<User> userBaseResponse) {
                        if (userBaseResponse.code == 0) {
                            view.loginSuccess(userBaseResponse.data);
                        } else {
                            HttpException exception = new HttpException(null);
                            exception.code = 1;
                            exception.message = userBaseResponse.msg;
                            view.loginFailed(exception);
                        }
                    }

                    @Override
                    public void onError(HttpException e) {
                        view.loginFailed(e);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        addSubscribe(d);
                    }
                });
    }
}