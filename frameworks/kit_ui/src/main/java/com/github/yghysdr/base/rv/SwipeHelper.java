package com.github.yghysdr.base.rv;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * 使用SwipeRefreshLayout来实现下拉刷新不包含列表的界面
 * 装饰着模式
 * <p>
 * 调用onRefresh 进项刷新
 */
public class SwipeHelper implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    public Helper mHelper;

    public SwipeHelper(SwipeRefreshLayout refreshLayout, Helper helper) {
        mSwipeRefreshLayout = refreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mHelper = helper;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mHelper.requestData();
    }

    public void stop(boolean canRefresh) {
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(canRefresh);
    }

    public interface Helper {
        void requestData();
    }

}
