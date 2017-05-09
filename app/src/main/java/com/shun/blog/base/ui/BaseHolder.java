package com.shun.blog.base.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 对Holder的封装
 *
 * @param <D> 数据模型
 */
public abstract class BaseHolder<D> extends RecyclerView.ViewHolder {

    protected Context mContext;

    public BaseHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    /**
     * 初始化数据
     *
     * @param data
     */
    public abstract void initDate(D data);

}
