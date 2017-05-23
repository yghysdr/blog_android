package com.shun.blog.ui.article.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.net.Error;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.bean.ArticleBean;
import com.shun.blog.ui.article.contract.ArticleListContract;
import com.shun.blog.ui.article.presenter.ArticleListAdapter;
import com.shun.blog.ui.article.presenter.ArticleListPresenterImpl;
import com.shun.blog.utils.ThemeUtil;
import com.shun.blog.weights.multistatelayout.MultiStateLayout;
import com.yghysdr.srecycleview.BaseBean;
import com.yghysdr.srecycleview.RecycleViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 */
public class ArticleListFragment extends BaseFragment<ArticleListPresenterImpl>
        implements ArticleListContract.View, RecycleViewHelper.Helper {

    private static final String ARG_PARAM1 = "com.shun.blog.article.list.type";

    private int mArticleType;

    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srl)
    SwipeRefreshLayout homeSrl;
    @BindView(R.id.home_multi)
    MultiStateLayout homeMulti;
    @BindView(R.id.article_ll)
    LinearLayout articleLl;

    private RecycleViewHelper mHelper;
    private boolean mHaveData;

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
    protected int getLayoutResource() {
        return R.layout.fragment_article;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        homeMulti.setState(MultiStateLayout.State.LOADING);
        mHelper = new RecycleViewHelper(mActivity, homeRv, new ArticleListAdapter(getActivity()),
                new LinearLayoutManager(getActivity()), homeSrl, this);
        mHelper.onRefresh();
        View networkErrorView = homeMulti.getNetworkErrorView();
        if (null != networkErrorView) {
            networkErrorView
                    .findViewById(R.id.status_reload)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mHelper.onRefresh();
                        }
                    });
        }
    }

    @Override
    public void onSuccess(List<ArticleBean> beanList) {
        List<BaseBean> baseBeanList = new ArrayList<>();
        for (ArticleBean bean : beanList) {
            baseBeanList.add(bean);
        }
        mHelper.addDataToView(baseBeanList);
        homeMulti.setState(MultiStateLayout.State.CONTENT);
        mHaveData = true;
    }

    @Override
    public void onFailed(int errorNo, String errorMsg) {
        if (!mHaveData) {
            if (errorNo == Error.NO_NET_ERROR) {
                homeMulti.setState(MultiStateLayout.State.NETWORK_ERROR);
            } else {
                homeMulti.setState(MultiStateLayout.State.ERROR);
            }
        }
        mHelper.stopRequest();
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestData(mArticleType, page, size);
    }

    @Override
    public int haveMoreData() {
        return mPresenter.haveMore();
    }

    @Override
    public void refreshTheme() {
        articleLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        int childCount = homeRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) homeRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
            TextView titleTv = (TextView) childView.findViewById(R.id.article_item_title_tv);
            titleTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            TextView contentTv = (TextView) childView.findViewById(R.id.article_item_content_tv);
            contentTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtContent));
            TextView desTv = (TextView) childView.findViewById(R.id.article_item_time_tv);
            desTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtDes));
        }
        ThemeUtil.dealRecycleView(homeRv);
    }

}
