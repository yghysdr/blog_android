package com.github.yghysdr.recycleview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 普通列表，
 * Created by yghysdr on 2017/5/8.
 * RecycleView+SwipeRefreshLayout 的封装，如刷新结束后怎么做等。
 * 实现原理：
 * SwipeRefreshLayout来监听顶部下拉，触发顶部刷新
 * 监听RecycleView的滚动，当滚动到底部的时候，触发底部请求下一页。
 * 使用方法：
 * 1.实现RecycleViewHelper.Helper接口。
 * -- 在Helper的requestData方法中处理请求请求网络。
 * 2 手动调用onRefresh(刷新数据)触发顶部刷新(该方法是SwipeRefreshLayout的一个)
 * 3 请求网网络数据调用addDataToView，将数据添加进来(本质是的Adapter中的List<Data>添加数据，
 * 然后调用adapter的notifyDataSetChanged方法刷新界面。
 * 4 使用者必须是SwipeRefreshLayout包裹的RecycleView。
 * 5 根据网络请求结果设置底部加载更多Holder的状态(setMoreStatus方法)
 * --请求结果正确
 * ----当data==null||data.size<每页请求数目   的时候，setMoreStatus(IStatus.NO_MORE)
 * ----否则调用setMoreStatus(IStatus.HAVE_MORE)
 * --请求结果错误时
 * ----调用setMoreStatus(IStatus.ERROR)
 * 6 当请求结果错误时，不调用addDataToView，此时必须调用stopRequest,来停止顶部和底部的刷新状态。
 */
public class RecycleViewHelper implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 每一页展示多少条数据
     */
    public static final int EVERY_PAGE_COUNT = 12;
    /**
     * 从第几页开始请求
     */
    public static final int START_PAGE = 1;
    public int refreshItemPosition = 5;

    protected Context mContext;
    protected RecyclerView mRecyclerView;
    protected BaseRVAdapter mBaseRVAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    /**
     * 当前是第几次请求
     */
    protected int mQuestCount = START_PAGE - 1;


    protected boolean mRequestingData = false;

    @IStatus
    protected int mDataStatus = IStatus.HAVE_MORE;

    protected final FoodModel mFoodModel;

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
        mFoodModel = new FoodModel();
        mHelper = helper;
        initListener();
    }

    public void setEmptyText(String text) {
        mFoodModel.empty = text;
    }

    public int getQuestCount() {
        return mQuestCount;
    }

    /**
     * 滚动到底部刷新的监听。
     */
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RVScrollListener() {
            @Override
            public void onScrollStatus(boolean isScrollDown, int firstVisible, int lastVisible, int newState) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScrollStatus(isScrollDown, firstVisible, lastVisible, newState);
                }
                if (isScrollDown && mLayoutManager.getItemCount() - refreshItemPosition <= lastVisible) {
                    readyData();
                }
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int dx, int dy) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScroll(recyclerView, dx, dy);
                }
            }
        });
        mBaseRVAdapter.createHolderListener(baseFooterHolder ->
                baseFooterHolder.addListener(() -> {
                    setMoreStatus(IStatus.HAVE_MORE);
                    readyData();
                }));
    }

    protected IOnScrollListener mOnScrollListener;

    public void addOnScrollListener(IOnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        setMoreStatus(IStatus.HAVE_MORE);
        mQuestCount = START_PAGE - 1;
        readyData();
    }

    protected void readyData() {
        if (mDataStatus == IStatus.HAVE_MORE && !mRequestingData) {
            mQuestCount++;
            mRequestingData = true;
            if (mHelper != null) {
                mHelper.requestData(mQuestCount, EVERY_PAGE_COUNT);
            } else {
                stopRequest(true);
            }
        }
    }

    /**
     * 数据加载成功后将数据添加到这里
     *
     * @param beanList
     */
    public void addDataToView(List beanList) {
        addDataToView(beanList, false);
    }

    /**
     * @param beanList
     * @param isCache  如果是通过缓存加载的数据，则没有请求网络，此时需要将数据请求次数添加一
     */
    public void addDataToView(List beanList, boolean isCache) {
        if (isCache) {
            mQuestCount++;
        }
        if (mQuestCount <= START_PAGE) {
            stopRequestStatus();
            mBaseRVAdapter.clear();
            mBaseRVAdapter.addDataList(beanList);
            if (mFoodModel != null) {
                if (mBaseRVAdapter.getItemCount() <= 0) {
                    mFoodModel.status = IStatus.EMPTY;
                }
                mBaseRVAdapter.addData(mFoodModel);
            }
            mBaseRVAdapter.notifyDataSetChanged();
        } else {
            stopRequest(false);
            if (mFoodModel != null) {
                mBaseRVAdapter.addDataListByPositionNotify(mBaseRVAdapter.getItemCount() - 1, beanList);
            } else {
                mBaseRVAdapter.addDataListNotify(beanList);
            }
        }
    }

    public void setMoreStatus(@IStatus int status) {
        mDataStatus = status;
        if (mFoodModel != null) {
            mFoodModel.status = status;
        }
    }

    /**
     * 停止页面请求状态
     */
    protected void stopRequestStatus() {
        mRequestingData = false;
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(true);
    }

    /**
     * 停止请求，并且更新Footer状态UI
     *
     * @param haveError 是否有错误信息
     */
    public void stopRequest(boolean haveError) {
        stopRequestStatus();
        if (haveError) {
            mQuestCount--;
            setMoreStatus(IStatus.ERROR);
        }
        if (mBaseRVAdapter.getItemCount() < 1 && mFoodModel != null) {
            mBaseRVAdapter.addDataNotify(mFoodModel);
        } else {
            mBaseRVAdapter.notifyItemChanged(mBaseRVAdapter.getItemCount() - 1);
        }
    }

    public Helper mHelper;

    public interface Helper {
        void requestData(int page, int size);
    }

    public boolean isRequest() {
        return mRequestingData;
    }
}
