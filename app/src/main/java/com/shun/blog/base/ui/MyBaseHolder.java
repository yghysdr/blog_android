package com.shun.blog.base.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.yghysdr.srecycleview.BaseHolder;

import butterknife.ButterKnife;

/**
 * Created by yghysdr on 2017/5/11.
 */

public abstract class MyBaseHolder<D> extends BaseHolder<D> {

    public MyBaseHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
        ButterKnife.bind(this, itemView);
    }
}
