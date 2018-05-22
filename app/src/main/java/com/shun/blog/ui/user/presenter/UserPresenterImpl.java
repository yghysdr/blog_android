package com.shun.blog.ui.user.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.event.LoginEvent;
import com.shun.blog.ui.user.contract.UserContract;
import com.shun.blog.ui.user.model.UserModelImpl;
import com.shun.blog.ui.user.view.UserFragment;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/05/18
 */

public class UserPresenterImpl extends BasePresenter<UserFragment, UserModelImpl>
        implements UserContract.Presenter {
    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addSubscription(RxBus
                .getDefault()
                .toObservable(LoginEvent.class)
                .delay(3, TimeUnit.SECONDS)
                .compose(RxSchedulers.<LoginEvent>io_main())
                .subscribe(new Action1<LoginEvent>() {
                    @Override
                    public void call(LoginEvent loginEvent) {
                        mView.initUserInfo();
                    }
                }));
    }
}