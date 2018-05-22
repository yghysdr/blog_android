package com.shun.blog.ui.main.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.main.contract.IndexContract;
import com.shun.blog.ui.main.model.IndexModelImpl;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/05/19
 */

public class IndexPresenterImpl extends BasePresenter<IndexContract.View, IndexModelImpl>
        implements IndexContract.Presenter {

    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addSubscription(RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Action1<ThemeEvent>() {
                    @Override
                    public void call(ThemeEvent themeEvent) {
                        mView.refreshTheme();
                    }
                }));
    }
}