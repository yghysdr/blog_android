package com.github.yghysdr.base.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by yghysdr on 2018/3/19.
 */

public class BaseService extends Service {

    //多P模式
    protected PresenterManager mPresenterList;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        mPresenterList = new PresenterManager();
        addPresenter(mPresenterList.mPresenterList);
        mPresenterList.attach();
    }

    protected void addPresenter(List<BasePresenter> mPresenterList) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenterList.detach();
    }
}
