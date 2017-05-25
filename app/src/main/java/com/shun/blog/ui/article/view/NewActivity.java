package com.shun.blog.ui.article.view;

import com.shun.blog.BaseApplication;
import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.ui.article.contract.NewContract;
import com.shun.blog.ui.article.presenter.NewPresenterImpl;

public class NewActivity extends BaseActivity<NewPresenterImpl>
        implements NewContract.View {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_new;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar(BaseApplication.getResString(R.string.title_article_new));
    }
}
