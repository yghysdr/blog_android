package com.shun.blog.ui.main.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.event.JumpEvent;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.main.contract.MainContract;
import com.shun.blog.ui.main.model.MainModelImpl;
import com.shun.blog.ui.main.view.MainActivity;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/05/23
 */

public class MainPresenterImpl extends BasePresenter<MainActivity, MainModelImpl>
        implements MainContract.Presenter {
    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addAsync(RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Action1<ThemeEvent>() {
                    @Override
                    public void call(ThemeEvent themeEvent) {
                        mView.changeTheme();
                    }
                }));

        mRxManage.addAsync(RxBus.getDefault()
                .toObservable(JumpEvent.class)
                .subscribe(new Action1<JumpEvent>() {
                    @Override
                    public void call(JumpEvent event) {
                        mView.jumpPage(event);
                    }
                }));
    }


}