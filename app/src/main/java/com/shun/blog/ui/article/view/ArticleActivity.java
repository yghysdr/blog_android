package com.shun.blog.ui.article.view;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.utils.ShareUtils;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.article_share:
                ShareUtils.shareUrl(getPackageName(),getLocalClassName(),"内容","title","subject");
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
//                intent.putExtra(Intent.EXTRA_TEXT, "默认分享的内容");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent = Intent.createChooser(intent, "系统分享对话框的标题");
//                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
