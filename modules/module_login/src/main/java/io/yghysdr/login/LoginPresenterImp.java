package io.yghysdr.login;

import android.content.Context;

import com.github.yghysdr.http.ApiRetrofit;
import com.github.yghysdr.http.DialogObserver;
import com.github.yghysdr.http.HttpException;
import io.blog.modle.BaseResponse;
import io.blog.modle.bean.User;
import com.github.yghysdr.base.ui.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
                .doOnNext(userBaseResponse -> MediatorUser.getUserProvider().updateUser(userBaseResponse.data))
                .subscribe(new DialogObserver<BaseResponse<User>>(context) {
                    @Override
                    public void onNext(BaseResponse<User> userBaseResponse) {
                        view.loginSuccess(userBaseResponse.data);
                    }

                    @Override
                    public void onError(HttpException e) {
                        view.loginFailed();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        addSubscribe(d);
                    }
                });
    }
}