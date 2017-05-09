package com.shun.blog.base.weight;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.shun.blog.bean.BaseBean;

import java.util.Collection;

/**
 * Created by yghysdr on 2017/5/8.
 * 需要实现加载更多的接口
 */

public class RecycleViewHelper implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 每一页展示多少条数据
     */
    public static final int EVERY_PAGE_COUNT = 10;

    Context mContext;
    RecyclerView mRecyclerView;
    BaseRVAdapter mBaseRVAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 当前是第几次请求
     */
    public int mQuestNum = 0;

    /**
     * 0 未请求数据
     * 1
     */
    private int mRequestStatus = 0;

    public boolean mHaveMoreDate = false;
    private final FooterHolder mHolder;

    public RecycleViewHelper(Context context,
                             RecyclerView recyclerView,
                             BaseRVAdapter adapter,
                             RecyclerView.LayoutManager layoutManager,
                             SwipeRefreshLayout refreshLayout,
                             Helper helper) {
        mContext = context;
        mRecyclerView = recyclerView;
        mLayoutManager = layoutManager;
        mSwipeRefreshLayout = refreshLayout;
        mBaseRVAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mHolder = new FooterHolder(mContext, null);
        mHelper = helper;
        onRefresh();
        initListener();
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RVScrollListener() {
            @Override
            public void onScrollStatus(boolean isScrollDown, int firstVisible, int lastVisible) {
                if (isScrollDown && mLayoutManager.getItemCount() - 1 <= lastVisible) {
                    readyData();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mHaveMoreDate = true;
        mQuestNum = 0;
        readyData();
    }

    public void readyData() {
        if (mHaveMoreDate && mRequestStatus == 0) {
            mQuestNum++;
            mRequestStatus = 1;
            if (mHelper != null) {
                mHelper.requestData(mQuestNum, EVERY_PAGE_COUNT);
            } else {
                stopRequest();
            }
        }
    }

    /**
     * 数据加载成功后将数据添加到这里
     *
     * @param beanList
     */
    public void addDataToView(Collection<BaseBean> beanList) {
        mRequestStatus = 0;
        stopRequest();
        if (mQuestNum == 1) {
            mBaseRVAdapter.clear();
            mBaseRVAdapter.addAll(beanList);
            mBaseRVAdapter.setFooterView(mHolder);
        } else {
            mBaseRVAdapter.addAll(beanList);
        }
        if (mHelper != null) {
            mHaveMoreDate = mHelper.haveMoreData();
        } else {
            mHaveMoreDate = false;
        }
        if (mHaveMoreDate) {
            mHolder.setStatus(0);
        } else {
            mHolder.setStatus(1);
        }
    }

    public void stopRequest() {
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(true);
    }

    public Helper mHelper;

    public interface Helper {
        void requestData(int page, int size);

        boolean haveMoreData();
    }
}
