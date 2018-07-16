package io.yghysdr.article.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import butterknife.BindView;
import io.blog.res.bean.Tag;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.BaseFragment;
import com.github.yghysdr.theme.ThemeEvent;
import com.github.yghysdr.theme.ThemeUtil;
import com.github.yghysdr.util.UIUtils;

import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import io.yghysdr.article.contract.TagContract;
import io.yghysdr.article.presenter.TagPresenterImpl;

import com.github.yghysdr.widget.TagsLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.yghysdr.mediator.article.IConstantArticle;

/**
 * 归档
 */

@Route(path = IConstantArticle.ARTICLE_FRAGMENT_TAG)
public class TagFragment extends BaseFragment
        implements TagContract.View {

    @BindView(R2.id.tag_tl)
    TagsLayout tagTl;
    @BindView(R2.id.tag_root_fl)
    FrameLayout tagRootFl;

    private TagContract.Presenter mTagPresenter;


    public static TagFragment newInstance() {
        return new TagFragment();
    }


    @Override
    public void addPresenter(List list) {
        super.addPresenter(list);
        mTagPresenter = new TagPresenterImpl();
        list.add(mTagPresenter);
    }

    @Override
    protected void LazyLoad() {
        mTagPresenter.requestTag(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.article_fragment_tag;
    }

    private List<Tag> mTags;

    @Override
    public void addTags(List<Tag> tags) {
        mTags = tags;
        tagTl.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            final Tag tag = tags.get(i);
            TextView tv = (TextView) LayoutInflater
                    .from(mActivity)
                    .inflate(R.layout.article_item_view_tag, null);
            tv.setText(tag.name + "(" + tag.count + ")");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(
                    UIUtils.dip2Px(BaseApp.getContext(), 20),
                    UIUtils.dip2Px(BaseApp.getContext(), 10),
                    0,
                    0
            );
            tv.setLayoutParams(layoutParams);
            tagTl.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventTheme(ThemeEvent event) {
        switch (event.intent) {
            case ThemeEvent.CHANGE:
                tagRootFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
                if (mTags != null) {
                    addTags(mTags);
                }
        }
    }

}
