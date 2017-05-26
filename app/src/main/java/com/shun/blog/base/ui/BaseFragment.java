package com.shun.blog.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.shun.blog.R;
import com.shun.blog.utils.TUtil;
import com.shun.blog.utils.ThemeUtil;

import butterknife.ButterKnife;

/**
 * Created by yghysdr on 16/12/1.
 * 1 如果是mvp模式只需要实现泛型P即可
 * 2 该fragment是否被复用。通常结合FragmentPagerAdapter
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected BaseActivity mActivity;
    protected View mRootView;
    protected P mPresenter;
    protected boolean mReUse = false;
    //懒加载
    private boolean isViewCreated, isUIVisible;
    private Toolbar mToolbar;


    //onAttach--onCreate--onCreateView--onViewCreated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mReUse && mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, mRootView);
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.mContext = getActivity();
        }
        isViewCreated = true;
        isLazyLoad();
        beforeReturn();
        return mRootView;
    }

    /**
     * 返回之前的操作，如果需要集成
     */
    public void beforeReturn() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            isLazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void isLazyLoad() {
        if (isViewCreated && isUIVisible) {
            lazyLoad();
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * 懒加载
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
        mActivity = (BaseActivity) context;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mReUse) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected void initToolBar(CharSequence title) {
        initToolBar(title, false);
    }

    protected void initToolBar(CharSequence title, boolean haveBack) {
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
            mToolbar.setTitle(title);
            if (haveBack) {
                mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mActivity.onBackPressed();
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    protected void refreshToolbar() {
        if (mToolbar != null) {
            mToolbar.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
            mToolbar.setTitleTextColor(ThemeUtil.getColorId(ThemeUtil.txtNav));
        }
    }

    protected abstract int getLayoutResource();
}
