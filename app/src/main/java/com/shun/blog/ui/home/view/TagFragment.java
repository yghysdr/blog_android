package com.shun.blog.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.bean.Tag;
import com.shun.blog.event.JumpEvent;
import com.shun.blog.ui.home.contract.TagContract;
import com.shun.blog.ui.home.presenter.TagPresenterImpl;
import com.shun.blog.utils.DensityUtil;
import com.shun.blog.weights.TagsLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 归档
 */
public class TagFragment extends BaseFragment<TagPresenterImpl>
        implements TagContract.View {

    @BindView(R.id.tag_tl)
    TagsLayout tagTl;

    public TagFragment() {
        mReUse = true;
    }

    public static TagFragment newInstance() {
        return new TagFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.requestTag();
    }

    @Override
    public void addTags(List<Tag> tags) {
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
                    DensityUtil.dip2px(mActivity, 20),
                    DensityUtil.dip2px(mActivity, 10),
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

    }

}
