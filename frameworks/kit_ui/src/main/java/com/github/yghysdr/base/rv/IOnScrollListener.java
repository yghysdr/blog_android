package com.github.yghysdr.base.rv;

import android.support.v7.widget.RecyclerView;

public interface IOnScrollListener {

    void onScrollStatus(boolean isScrollDown, int firstVisible, int lastVisible, int newState);

    void onScroll(RecyclerView recyclerView, int dx, int dy);
}
