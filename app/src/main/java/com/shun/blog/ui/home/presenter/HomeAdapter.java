package com.shun.blog.ui.home.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.shun.blog.ui.home.view.HomeHolder;
import com.yghysdr.srecycleview.BaseHolder;
import com.yghysdr.srecycleview.BaseRVAdapter;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class HomeAdapter extends BaseRVAdapter {

    public HomeAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(mContext, parent);
    }

}