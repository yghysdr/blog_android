package com.shun.blog.ui.user.presenter;

import com.shun.blog.base.BaseResponse;
import com.shun.blog.base.mvp.BaseMvpPresenter;
import com.shun.blog.baserx.ApiCallback;
import com.shun.blog.baserx.RxSchedulers;
import com.shun.blog.bean.User;
import com.shun.blog.ui.user.activity.LoginActivity;
import com.shun.blog.ui.user.contract.LoginContract;
import com.shun.blog.ui.user.model.LoginModelImpl;

import java.util.concurrent.TimeUnit;

/**
 * Created by yghysdr on 2017/04/27
 */

public class LoginPresenterImpl extends BaseMvpPresenter<LoginActivity,
        LoginModelImpl> implements LoginContract.Presenter {


    @Override
    public void login(User user) {
        mRxManage.addAsync(mMode
                .login(user)
                .compose(RxSchedulers.<BaseResponse<User>>io_main())
                .delay(1, TimeUnit.SECONDS)
                .subscribe(new ApiCallback<BaseResponse<User>>() {
                    @Override
                    public void onSuccess(BaseResponse<User> result) {
                        mView.loginSuccess(result.data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        super.onFailure(code, msg);
                        mView.loginFailed();
                    }
                }));

    }
}