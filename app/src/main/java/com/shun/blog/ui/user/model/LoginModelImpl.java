package com.shun.blog.ui.user.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseModel;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.User;
import com.shun.blog.ui.user.contract.LoginContract;

import rx.Observable;

/**
 * Created by yghysdr on 2017/04/27
 */

public class LoginModelImpl extends BaseModel
        implements LoginContract.Model {

    @Override
    public Observable<BaseResponse<User>> login(User user) {
        return AppWebClient
                .getApiStores(HostType.BLOG)
                .login(user);
    }
}