package com.shun.blog.base.ui;

import com.shun.blog.utils.TUtil;

/**
 * Created by yghysdr on 2016/9/20.
 * Mvp中基本View的Fragment的基类
 * 所有的Fragment继承BaseMvpFragment，并实现定义一个接口实现自己特有等方法
 */
public abstract class BaseMvpFragment<P extends BaseMvpPresenter, M extends BaseModel>
        extends BaseFragment {

    protected P mPresenter;

    @Override
    public void beforeReturn() {
        super.beforeReturn();
        if (mPresenter == null) {
            mPresenter = TUtil.getT(this, 0);
        }
        mPresenter.attachView(this);
        mPresenter.mMode = TUtil.getT(this, 1);
        mPresenter.mContext = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
