package com.shun.blog.ui.article.view;

import android.view.KeyEvent;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.weights.MyWebView;

import butterknife.BindView;

public class ArticleActivity extends BaseActivity {

    public static final String TITLE = "com.shun.blog.ui.article.view.title";
    public static final String URL = "com.shun.blog.ui.article.view.url";

    @BindView(R.id.article_content_wv)
    MyWebView articleContentWv;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_article;
    }

    @Override
    protected void init() {
        super.init();
        String mTitle = getIntent().getStringExtra(TITLE);
        String mUrl = getIntent().getStringExtra(URL);
        initToolBar(mTitle);
        articleContentWv.loadUrl(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && articleContentWv.canGoBack()) {
            articleContentWv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
