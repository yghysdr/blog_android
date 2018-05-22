package com.shun.blog.ui.discover.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.discover.contract.DiscoverContract;
import com.shun.blog.ui.discover.model.DiscoverModelImpl;
import com.shun.blog.ui.discover.view.DiscoverFragment;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/05/19
 */

public class DiscoverPresenterImpl extends BasePresenter<DiscoverFragment, DiscoverModelImpl>
        implements DiscoverContract.Presenter {
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