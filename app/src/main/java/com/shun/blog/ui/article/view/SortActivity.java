package com.shun.blog.ui.article.view;

import android.widget.FrameLayout;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.bean.Tag;

import butterknife.BindView;

public class SortActivity extends BaseActivity {

    public static final String SORT_TAG = "com.shun.blog.ui.article.view.sort.activity";

    @BindView(R.id.sort_fl)
    FrameLayout sortFl;
    private Tag mTag;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_sort;
    }

    @Override
    protected void init() {
        super.init();
        mTag = (Tag) getIntent().getSerializableExtra(SORT_TAG);
        if (mTag == null) {
            mTag = new Tag();
            mTag.id = 0;
            mTag.name = "android";
        }
        initToolBar(mTag.name);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sort_fl, ArticleListFragment.newInstance(mTag.id, true))
                .commit();
    }

}
