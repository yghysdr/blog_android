package com.shun.blog.ui.article.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.MyBaseHolder;
import com.shun.blog.bean.ArticleBean;
import com.shun.blog.utils.DateUtil;
import com.shun.blog.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArchiveHolder extends MyBaseHolder<ArticleBean> {


    @BindView(R.id.archive_title)
    TextView archiveTitle;
    @BindView(R.id.archive_time)
    TextView archiveTime;

    public ArchiveHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_archive_article);
    }

    @Override
    public void initData(ArticleBean data) {
        archiveTitle.setText(StringUtils.getArticleContent(data.title));
        archiveTime.setText(DateUtil.long2Str(data.createdAt, DateUtil.FORMAT_MD));
    }
}
