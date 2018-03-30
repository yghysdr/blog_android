package com.shun.blog.ui.article.view;

import com.shun.blog.App;
import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.article.contract.NewContract;

public class NewActivity extends BaseActivity
        implements NewContract.View {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_new;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar(App.getResString(R.string.title_article_new));
    }
}
