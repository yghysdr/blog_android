package io.yghysdr.article.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import com.github.yghysdr.recycleview.BaseRVAdapter;
import com.github.yghysdr.theme.ThemeUtil;

import io.yghysdr.article.R;
import io.yghysdr.article.contract.ArticleListContract;
import io.yghysdr.article.presenter.ArticleListAdapter;
import io.yghysdr.article.presenter.ArticleListPresenterImpl;
import io.yghysdr.blog.common.BaseListFragment;
import io.yghysdr.mediator.article.IConstantArticle;

/**
 */
@Route(path = IConstantArticle.ARTICLE_FRAGMENT_ARTICLE)
public class ArticleListFragment extends BaseListFragment
        implements ArticleListContract.View {

    private ArticleListContract.Presenter mPresenter;

    private int mArticleType;


    public static ArticleListFragment newInstance(int type) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(IConstantArticle.ARG_ARTICLE_LIST_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArticleType = getArguments().getInt(IConstantArticle.ARG_ARTICLE_LIST_TYPE, 0);
        }
    }


    @Override
    public BaseRVAdapter getAdapter() {
        return new ArticleListAdapter(getActivity());
    }

    @Override
    public void addPresenter(List list) {
        super.addPresenter(list);
        mPresenter = new ArticleListPresenterImpl();
        list.add(mPresenter);
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestList(this, page, size, mArticleType);
    }

    @Override
    public void refreshTheme() {
        baseRootLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        /**
         * 得到的是在视图中的item条数
         */
        int childCount = baseRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) baseRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));

            TextView titleTv = (TextView) childView.findViewById(R.id.article_item_title_tv);
            if (titleTv != null) {
                titleTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
            }

            View desV = childView.findViewById(R.id.article_item_des_v);
            if (desV != null) {
                desV.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
            }

            TextView desTv = (TextView) childView.findViewById(R.id.article_item_des_tv);
            if (desTv != null) {
                desTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtContent));
            }

            TextView timeTv = (TextView) childView.findViewById(R.id.article_item_time_tv);
            if (timeTv != null) {
                timeTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtDes));
            }
        }
        ThemeUtil.dealRecycleView(baseRv);
    }


}
