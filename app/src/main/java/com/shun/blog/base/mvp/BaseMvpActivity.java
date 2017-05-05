package com.shun.blog.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shun.blog.base.BaseActivity;
import com.shun.blog.base.BaseModel;
import com.shun.blog.utils.TUtil;

/**
 * Created by yghysdr on 2016/9/20.
 * Mvp中基本View的Activity的基类
 * 所有的Activity继承BaseMvpActivity，并实现定义一个接口实现自己特有等方法
 * M只是为了绑定，view并没有拥有该对象
 */
public abstract class BaseMvpActivity<P extends BaseMvpPresenter, M extends BaseModel>
        extends BaseActivity {

    protected P mPresenter;


    /**
     * 绑定presenter
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = TUtil.getT(this, 0);
        mPresenter.attachView(this);
        mPresenter.mMode = TUtil.getT(this, 1);
        mPresenter.mActivity = this;
        super.onCreate(savedInstanceState);
    }

    /**
     * 解绑presenter，防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
