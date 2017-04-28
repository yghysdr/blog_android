package com.shun.blog.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yghysdr on 16/12/1.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected View mRootView;
    /**
     * 该fragment是否被复用。通常结合FragmentPagerAdapter
     */
    protected boolean mRepeatUse = false;

    /**
     * 在onCreate之后执行
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView != null && mRepeatUse) {
            return mRootView;
        }
        mRootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, mRootView);
        beforeReturn();
        return mRootView;
    }

    /**
     * 返回之前的操作，如果需要集成
     */
    private void beforeReturn() {
    }

    protected abstract int getLayoutResource();

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 延迟加载 * 子类必须重写此方法 取消预加载实现此方法
     */
    protected void lazyLoad() {

    }

    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRepeatUse) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
