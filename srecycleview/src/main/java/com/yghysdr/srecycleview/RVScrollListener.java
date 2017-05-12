package com.yghysdr.srecycleview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by yghysdr on 2016/12/13.
 * 监听滚动情况
 */

public abstract class RVScrollListener extends RecyclerView.OnScrollListener {

    private boolean mIsScrollDown;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mIsScrollDown = dy > 0;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (mLayoutManager == null) {
            mLayoutManager = recyclerView.getLayoutManager();
        }
        if (mLayoutManager == null) {
            throw new RuntimeException("no LayoutManager");
        }
        onScrollStatus(mIsScrollDown, getFirstVisiblePosition(), getLastVisiblePosition());
    }

    private int getLastVisiblePosition() {
        /**
         * GridLayoutManager extends LinearLayoutManager
         */
        int position;
        if (mLayoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] lastPositions = manager
                    .findLastVisibleItemPositions(new int[manager.getSpanCount()]);
            position = getMax(lastPositions);
        } else {
            position = mLayoutManager.getItemCount() - 1;
        }
        return position;
    }

    private int getFirstVisiblePosition() {
        int position;
        if (mLayoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mLayoutManager)
                    .findFirstCompletelyVisibleItemPosition();
        } else {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] firstPositions = manager
                    .findFirstCompletelyVisibleItemPositions(new int[manager.getSpanCount()]);
            position = getMin(firstPositions);
        }
        return position;
    }

    /**
     * 获得最大的位置
     */
    private int getMin(int[] positions) {
        int min = positions[0];
        for (int value : positions) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * 获取数组中的最大值
     *
     * @param positions 需要找到最大值的数组
     * @return 数组中的最大值
     */
    private int getMax(int[] positions) {
        int max = positions[0];
        for (int value : positions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public abstract void onScrollStatus(boolean isScrollDown, int firstVisible, int lastVisible);
}
