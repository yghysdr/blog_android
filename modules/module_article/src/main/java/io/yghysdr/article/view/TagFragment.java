package io.yghysdr.article.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import butterknife.BindView;
import io.blog.modle.bean.Tag;
import io.blog.modle.event.JumpEvent;
import com.github.yghysdr.base.ui.BaseFragment;
import com.github.yghysdr.base.utils.UIUtils;
import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import io.yghysdr.article.contract.TagContract;
import io.yghysdr.article.presenter.TagPresenterImpl;
import io.yghysdr.common.RxBus;
import io.yghysdr.common.common.util.ThemeUtil;
import io.yghysdr.common.widget.TagsLayout;
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
        return R.layout.fragment_tag;
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
                    .inflate(R.layout.item_view_tag, null);
            tv.setText(tag.name + "(" + tag.count + ")");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(
                    UIUtils.dip2Px(20),
                    UIUtils.dip2Px(10),
                    0,
                    0
            );
            tv.setLayoutParams(layoutParams);
            tagTl.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpEvent jumpEvent = new JumpEvent();
                    jumpEvent.order = JumpEvent.SORT;
                    jumpEvent.tag = tag;
                    RxBus.getDefault().post(jumpEvent);
                }
            });
        }
    }

    @Override
    public void refreshTheme() {
        tagRootFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        if (mTags != null) {
            addTags(mTags);
        }
    }
}
