package io.yghysdr.article.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import io.blog.res.bean.Article;
import com.github.yghysdr.recycleview.BaseRVHolder;
import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import com.github.yghysdr.util.DateUtil;
import io.yghysdr.article.StringUtils;
import io.yghysdr.mediator.article.MediatorArticle;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListHolder extends BaseRVHolder<Article> {
    @BindView(R2.id.article_item_title_tv)
    TextView articleItemTitleTv;
    @BindView(R2.id.article_item_des_v)
    View articleItemDesV;
    @BindView(R2.id.article_item_des_tv)
    TextView articleItemDesTv;
    @BindView(R2.id.article_item_time_tv)
    TextView articleItemTimeTv;
    @BindView(R2.id.article_item_cv)
    CardView articleItemCv;


    public ArticleListHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.article_item_article);
    }

    @Override
    public void initData(Article data) {
        articleItemTitleTv.setText(StringUtils.getArticleContent(data.title));
        articleItemDesTv.setText(StringUtils.getArticleDes(data.des));
        articleItemTimeTv.setText(DateUtil.long2Str(data.updatedAt, DateUtil.FORMAT_YMD));
        itemView.setOnClickListener(v -> {
            MediatorArticle.startArticleActivity(data.id, "文章详情");
        });
    }
}
