package com.shun.blog.ui.user.presenter;

import android.content.Context;

import com.shun.blog.base.net.DialogCallback;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.User;
import com.shun.blog.ui.user.contract.LoginContract;
import com.shun.blog.ui.user.model.LoginModelImpl;

/**
 * Created by yghysdr on 2017/04/27
 */

public class LoginPresenterImpl extends BasePresenter<LoginContract.View,
        LoginModelImpl> implements LoginContract.Presenter {

    @Override
    public void login(User user, Context context) {
        mRxManage.addAsync(mMode
                .login(user)
                .compose(RxSchedulers.<BaseResponse<User>>io_main())
                .subscribe(new DialogCallback<BaseResponse<User>>(context) {
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