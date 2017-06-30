package com.shun.blog.ui.article.view;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseListFragment;
import com.shun.blog.ui.article.contract.ArticleListContract;
import com.shun.blog.ui.article.presenter.ArticleListAdapter;
import com.shun.blog.ui.article.presenter.ArticleListPresenterImpl;
import com.shun.blog.utils.ThemeUtil;
import com.yghysdr.srecycleview.BaseRVAdapter;
import com.yghysdr.srecycleview.RecycleViewHelper;

import java.util.List;

/**
 */
public class ArticleListFragment extends BaseListFragment<ArticleListPresenterImpl>
        implements ArticleListContract.View, RecycleViewHelper.Helper {

    private static final String ARG_PARAM1 = "com.shun.blog.article.list.type";

    private int mArticleType;


    public ArticleListFragment() {
        mReUse = true;
    }

    public static ArticleListFragment newInstance(int type) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArticleType = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public BaseRVAdapter getAdapter() {
        return new ArticleListAdapter(getActivity());
    }

    @Override
    public void addDateToList(Object beanList) {
        List<Object> articleBeanList = (List<Object>) beanList;
        mHelper.addDataToView(articleBeanList);
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestData(mArticleType, page, size);
    }

    @Override
    public void refreshTheme() {
        mBaseRootLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        int childCount = mBaseRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) mBaseRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));

            TextView titleTv = (TextView) childView.findViewById(R.id.article_item_title_tv);
            if (titleTv != null) {
                titleTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            }

            TextView desTv = (TextView) childView.findViewById(R.id.article_item_des_tv);
            if (desTv != null) {
                desTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtContent));
                desTv.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
            }

            FrameLayout itemDesFl = (FrameLayout) childView.findViewById(R.id.article_item_des_fl);
            if (itemDesFl != null) {
                itemDesFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
            }

            TextView timeTv = (TextView) childView.findViewById(R.id.article_item_time_tv);
            if (timeTv != null) {
                timeTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtDes));
            }
        }
        ThemeUtil.dealRecycleView(mBaseRv);
    }

}
