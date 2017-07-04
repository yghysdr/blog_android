package com.shun.blog.ui.article.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.MyBaseHolder;
import com.shun.blog.bean.Article;
import com.shun.blog.event.JumpEvent;
import com.shun.blog.utils.DateUtil;
import com.shun.blog.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListHolder extends MyBaseHolder<Article> {

    @BindView(R.id.article_item_title_tv)
    TextView articleItemTitleTv;
    @BindView(R.id.article_item_des_tv)
    TextView articleItemDesTv;
    @BindView(R.id.article_item_time_tv)
    TextView articleItemTimeTv;

    public ArticleListHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_home);
    }

    @Override
    public void initData(final Article data) {
        articleItemTitleTv.setText(StringUtils.getArticleContent(data.title));
        articleItemDesTv.setText(StringUtils.getArticleDes(data.des));
        articleItemTimeTv.setText(DateUtil.long2Str(data.updatedAt, DateUtil.FORMAT_YMD));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpEvent event = new JumpEvent();
                event.order = JumpEvent.ARTICLE;
                event.articleId = data.id;
                RxBus.getDefault().post(event);
            }
        });
    }
}
