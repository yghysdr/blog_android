package com.shun.blog.ui.article.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseListFragment;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.ui.article.contract.ArticleListContract;
import com.shun.blog.ui.article.presenter.ArticleListAdapter;
import com.shun.blog.ui.article.presenter.ArticleListPresenterImpl;
import com.shun.blog.utils.ThemeUtil;
import com.yghysdr.srecycleview.BaseRVAdapter;
import com.yghysdr.srecycleview.RecycleViewHelper;

import java.util.List;

/**
 */
public class ArticleListFragment extends BaseListFragment
        implements ArticleListContract.View, RecycleViewHelper.Helper {

    private static final String ARG_PARAM1 = "com.shun.blog.article.list.type";
    private static final String ARG_PARAM2 = "com.shun.blog.article.list.is.viewpager";

    private int mArticleType;


    public ArticleListFragment() {
        mReUse = true;
    }

    public static ArticleListFragment newInstance(int type) {
        return newInstance(type, false);
    }

    public static ArticleListFragment newInstance(int type, boolean isViewPager) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        args.putBoolean(ARG_PARAM2, isViewPager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArticleType = getArguments().getInt(ARG_PARAM1, 0);
            if (!isUIVisible) {
                isUIVisible = getArguments().getBoolean(ARG_PARAM2, false);
            }
        }
    }

    @Override
    public void addPresenter(List<BasePresenter> basePresenters) {
        super.addPresenter(basePresenters);
        mBaseListPresenter = new ArticleListPresenterImpl();
        basePresenters.add(mBaseListPresenter);
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
        mBaseListPresenter.requestData(mArticleType, page, size);
    }

    @Override
    public void refreshTheme() {
        mBaseRootLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        /**
         * 得到的是在视图中的item条数
         */
        int childCount = mBaseRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) mBaseRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));

            TextView titleTv = (TextView) childView.findViewById(R.id.article_item_title_tv);
            if (titleTv != null) {
                titleTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            }

            View desV = childView.findViewById(R.id.article_item_des_v);
            if (desV != null) {
                desV.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
            }

            TextView desTv = (TextView) childView.findViewById(R.id.article_item_des_tv);
            if (desTv != null) {
                desTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtContent));
            }

            TextView timeTv = (TextView) childView.findViewById(R.id.article_item_time_tv);
            if (timeTv != null) {
                timeTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtDes));
            }

            TextView statusTv = (TextView) childView.findViewById(R.id.item_footer_status_tv);
            if (statusTv != null) {
                statusTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            }
        }
        ThemeUtil.dealRecycleView(mBaseRv);
    }

}
