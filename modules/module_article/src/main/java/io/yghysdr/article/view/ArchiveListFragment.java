package io.yghysdr.article.view;

import android.view.ViewGroup;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.multistatelayout.MultiStateLayout;
import io.blog.res.BaseResponse;
import io.blog.res.bean.Archive;

import com.github.yghysdr.recycleview.BaseRVAdapter;
import com.github.yghysdr.recycleview.IStatus;
import com.github.yghysdr.theme.ThemeUtil;

import io.yghysdr.blog.common.BaseListFragment;
import io.yghysdr.article.R;
import io.yghysdr.article.contract.ArchiveContract;
import io.yghysdr.article.presenter.ArchiveListAdapter;
import io.yghysdr.article.presenter.ArchiveListPresenterImpl;
import io.yghysdr.mediator.article.IConstantArticle;

/**
 * 归档
 */
@Route(path = IConstantArticle.ARTICLE_FRAGMENT_ARCHIVE)
public class ArchiveListFragment extends BaseListFragment
        implements ArchiveContract.View {

    ArchiveContract.Presenter mPresenter;


    public static ArchiveListFragment newInstance() {
        return new ArchiveListFragment();
    }

    @Override
    public void addPresenter(List basePresenters) {
        super.addPresenter(basePresenters);
        mPresenter = new ArchiveListPresenterImpl();
        basePresenters.add(mPresenter);
    }

    @Override
    public BaseRVAdapter getAdapter() {
        return new ArchiveListAdapter(mActivity);
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.getArchiveList(this, page, size);
    }

    @Override
    public void refreshTheme() {
        baseRootLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        int childCount = baseRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) baseRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));


            TextView titleTv = (TextView) childView.findViewById(R.id.archive_title);
            if (titleTv != null) {
                titleTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
            }

            TextView timeTv = (TextView) childView.findViewById(R.id.archive_time);
            if (timeTv != null) {
                timeTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtDes));
            }

            TextView yearTv = (TextView) childView.findViewById(R.id.archive_year);
            if (yearTv != null) {
                yearTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
            }
        }
        ThemeUtil.dealRecycleView(baseRv);
    }


    @Override
    public void onListSuccess(BaseResponse response) {
        mHelper.setMoreStatus(response.haveMore ? IStatus.HAVE_MORE : IStatus.NO_MORE);
        baseRootMulti.setState(MultiStateLayout.State.CONTENT);
        List<Archive> archiveList = (List<Archive>) response.data;
        List list = new ArrayList<>();
        for (Archive archive : archiveList) {
            list.add(archive.timestamp);
            list.addAll(archive.articleList);
        }
        mHelper.addDataToView(list);
    }

}
