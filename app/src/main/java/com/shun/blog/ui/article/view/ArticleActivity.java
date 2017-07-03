package com.shun.blog.ui.article.view;

import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.net.JsonCallback;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.ArticleBean;
import com.shun.blog.ui.article.model.ArticleModel;

import butterknife.BindView;

public class ArticleActivity extends BaseActivity {


    @BindView(R.id.article_content_tv)
    TextView articleContentTv;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_article;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar(getString(R.string.title_article));
        ArticleModel articleModel = new ArticleModel();
        articleModel
                .requestData(1)
                .compose(RxSchedulers.<BaseResponse<ArticleBean>>io_main())
                .subscribe(new JsonCallback<BaseResponse<ArticleBean>>() {
                    @Override
                    public void onSuccess(BaseResponse<ArticleBean> result) {
                    }
                });

    }
}
