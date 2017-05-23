package com.shun.blog.ui.home.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.home.contract.HomeContract;
import com.shun.blog.ui.home.model.HomeModelImpl;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/05/19
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View, HomeModelImpl>
        implements HomeContract.Presenter {

    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addAsync(RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Action1<ThemeEvent>() {
                    @Override
                    public void call(ThemeEvent themeEvent) {
                        mView.refreshTheme();
                    }
                }));
    }
}