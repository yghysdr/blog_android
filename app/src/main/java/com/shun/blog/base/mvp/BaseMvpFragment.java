package com.shun.blog.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shun.blog.base.BaseFragment;
import com.shun.blog.utils.TUtil;


/**
 * Created by yghysdr on 2016/9/20.
 * Mvp中基本View的Fragment的基类
 * 所有的Fragment继承BaseMvpFragment，并实现定义一个接口实现自己特有等方法
 */
public abstract class BaseMvpFragment<P extends BaseMvpPresenter>
        extends BaseFragment {

    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = TUtil.getT(this, 0);
        }
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
