package io.yghysdr.article.view;


import android.widget.FrameLayout;


import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import io.blog.modle.bean.Tag;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.ui.BaseActivity;

import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import io.yghysdr.mediator.article.IConstantArticle;

@Route(path = IConstantArticle.ARTICLE_ACTIVITY_SORT)
public class SortActivity extends BaseActivity {

    public static final String SORT_TAG = "SortActivity.sort.tag";

    @BindView(R2.id.sort_fl)
    FrameLayout sortFl;
    private Tag mTag;

    @Override
    public void initData() {
        super.initData();
        mTag = (Tag) getIntent().getSerializableExtra(SORT_TAG);
        if (mTag == null) {
            mTag = new Tag();
            mTag.id = 0;
            mTag.name = "android";
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sort_fl, ArticleListFragment.newInstance(mTag.id))
                .commit();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_sort;
    }
}
