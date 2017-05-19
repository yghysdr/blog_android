package com.shun.blog.ui.article.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.MyBaseHolder;
import com.shun.blog.bean.ArticleBean;

import butterknife.BindView;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListHolder extends MyBaseHolder<ArticleBean> {
    @BindView(R.id.home_item_title)
    TextView homeItemTitle;

    public ArticleListHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_home);
    }

    @Override
    public void initData(ArticleBean data) {
        homeItemTitle.setText(data.title);
    }
}
