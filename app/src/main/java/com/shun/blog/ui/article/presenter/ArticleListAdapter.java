package com.shun.blog.ui.article.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.shun.blog.ui.article.view.ArticleListHolder;
import com.yghysdr.srecycleview.BaseHolder;
import com.yghysdr.srecycleview.BaseRVAdapter;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListAdapter extends BaseRVAdapter {

    public ArticleListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleListHolder(mContext, parent);
    }

}