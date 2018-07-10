package io.yghysdr.article.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import io.blog.modle.bean.Article;
import com.github.yghysdr.base.rv.BaseRVHolder;
import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import io.yghysdr.common.common.util.DateUtil;
import io.yghysdr.common.common.util.StringUtils;
import io.yghysdr.mediator.article.MediatorArticle;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArchiveHolder extends BaseRVHolder<Article> {


    @BindView(R2.id.archive_title)
    TextView archiveTitle;
    @BindView(R2.id.archive_time)
    TextView archiveTime;

    public ArchiveHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_archive_article);
    }

    @Override
    public void initData(final Article data) {
        archiveTitle.setText(StringUtils.getArticleContent(data.title));
        archiveTime.setText(DateUtil.long2Str(data.createdAt, DateUtil.FORMAT_MD));
        itemView.setOnClickListener(v -> MediatorArticle.startArticleActivity(data.id, "文章详情"));
    }
}
