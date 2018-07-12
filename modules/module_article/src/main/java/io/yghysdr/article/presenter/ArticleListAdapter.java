package io.yghysdr.article.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.github.yghysdr.recycleview.BaseRVAdapter;
import com.github.yghysdr.recycleview.BaseRVHolder;

import io.yghysdr.article.view.ArticleListHolder;


/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListAdapter extends BaseRVAdapter {

    public ArticleListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseRVHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleListHolder(mContext, parent);
    }

}