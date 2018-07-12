package io.yghysdr.article.view;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import com.github.yghysdr.base.BaseActivity;

import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import io.yghysdr.article.ShareUtils;
import io.yghysdr.mediator.article.IConstantArticle;

@Route(path = IConstantArticle.ARTICLE_ACTIVITY_ARTICLE)
public class ArticleActivity extends BaseActivity {


    @BindView(R2.id.article_content_wv)
    MyWebView articleContentWv;


    @Override
    protected int provideContentViewId() {
        return R.layout.article_activity_article;
    }

    @Override
    public void initData() {
        super.initData();
        String mTitle = getIntent().getStringExtra(IConstantArticle.ARG_ARTICLE_DETAIL_TITLE);
        String mUrl = getIntent().getStringExtra(IConstantArticle.ARG_ARTICLE_DETAIL_URL);
        articleContentWv.loadUrl(mUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.article_share) {
            ShareUtils.shareUrl(getPackageName(), getLocalClassName(), "内容", "title", "subject");
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
