package com.shun.blog.ui.article.view;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;

public class ArticleActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_article;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar(getString(R.string.title_article));
    }

}
