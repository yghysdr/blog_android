package com.yghysdr.srecycleview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

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

    private Context mContext;
    private RecyclerView mRecyclerView;
    private BaseRVAdapter mBaseRVAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 当前是第几次请求
     */
    private int mQuestCount = 0;


    private boolean mRequestingData = false;

    @IFooter.Status
    private int mDataStatus = 0;

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
        initListener();
    }

    public int getQuestCount() {
        return mQuestCount;
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
        mDataStatus = IFooter.HAVE_MORE;
        mQuestCount = 0;
        readyData();
    }

    private void readyData() {
        if (mDataStatus == IFooter.HAVE_MORE && !mRequestingData) {
            mQuestCount++;
            mRequestingData = true;
            if (mHelper != null) {
                mHelper.requestData(mQuestCount, EVERY_PAGE_COUNT);
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
    public void addDataToView(Collection<Object> beanList) {
        stopRequest();
        if (mQuestCount == 1) {
            mBaseRVAdapter.clear();
            mBaseRVAdapter.addAll(beanList);
            mBaseRVAdapter.setFooterView(mHolder);
        } else {
            mBaseRVAdapter.addAll(beanList);
        }
    }

    public void stopRequest() {
        mRequestingData = false;
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(true);
        if (mHelper != null) {
            mDataStatus = mHelper.haveMoreData();
        } else {
            mDataStatus = IFooter.NO_MORE;
        }
        mHolder.setStatus(mDataStatus);
    }

    public Helper mHelper;

    public interface Helper {
        void requestData(int page, int size);

        @IFooter.Status
        int haveMoreData();
    }
}
