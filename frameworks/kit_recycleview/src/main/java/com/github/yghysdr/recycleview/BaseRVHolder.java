package com.github.yghysdr.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;

/**
 * 对recycleview.ViewHolder的封装
 * 对外提供
 */

public abstract class BaseRVHolder<D> extends RecyclerView.ViewHolder {

    protected Context mContext;
    protected D mData;


    public BaseRVHolder(Context context, ViewGroup root, int layoutRes) {
        this(context, LayoutInflater.from(context).inflate(layoutRes, root, false));
    }

    /**
     * 配合自定义holder，直接传递自定义holder，然后通过holder.getView
     *
     * @param context
     * @param view
     */
    public BaseRVHolder(Context context, View view) {
        super(view);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    /**
     * 初始化数据
     *
     * @param data
     */
    public void bindData(D data) {
        mData = data;
        if (BuildConfig.DEBUG) {
            initData(data);
        } else {
            //无奈，防止后端数据不合格【不要相信后端】
            try {
                initData(data);
            } catch (Exception e) {

            }
        }
    }

    public abstract void initData(D data);

}
