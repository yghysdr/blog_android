package com.yghysdr.srecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * 对Holder的封装
 *
 * @param <D> 数据模型
 */
public abstract class BaseHolder<D> extends RecyclerView.ViewHolder {

    protected Context mContext;
    protected int mPosition;
    protected D mData;

    public BaseHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        mContext = context;
    }

    /**
     * 初始化数据
     *
     * @param data
     */
    public void bindData(D data, int position) {
        mData = data;
        mPosition = position;
        initData(data);
    }

    public abstract void initData(D data);

}
