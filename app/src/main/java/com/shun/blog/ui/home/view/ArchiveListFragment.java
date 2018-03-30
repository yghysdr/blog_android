package com.shun.blog.ui.home.view;

import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseListFragment;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.bean.Archive;
import com.shun.blog.ui.home.contract.ArchiveContract;
import com.shun.blog.ui.home.presenter.ArchiveListAdapter;
import com.shun.blog.ui.home.presenter.ArchiveListPresenterImpl;
import com.shun.blog.utils.ThemeUtil;
import com.yghysdr.srecycleview.BaseRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 归档
 */
public class ArchiveListFragment extends BaseListFragment
        implements ArchiveContract.View {

    public ArchiveListFragment() {
        mReUse = true;
    }

    public static ArchiveListFragment newInstance() {
        return new ArchiveListFragment();
    }

    @Override
    public BaseRVAdapter getAdapter() {
        return new ArchiveListAdapter(mActivity);
    }

    @Override
    public void addDateToList(Object beanList) {
        List<Archive> archiveList = (List<Archive>) beanList;
        List<Object> objectList = new ArrayList<>();
        for (Archive archive : archiveList) {
            objectList.add(archive.timestamp);
            objectList.addAll(archive.articleList);
        }
        mHelper.addDataToView(objectList);
    }

    @Override
    public void refreshTheme() {
        mBaseRootLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        int childCount = mBaseRv.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) mBaseRv.getChildAt(childIndex);
            childView.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));


            TextView titleTv = (TextView) childView.findViewById(R.id.archive_title);
            if (titleTv != null) {
                titleTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            }

            TextView timeTv = (TextView) childView.findViewById(R.id.archive_time);
            if (timeTv != null) {
                timeTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtDes));
            }

            TextView yearTv = (TextView) childView.findViewById(R.id.archive_year);
            if (yearTv != null) {
                yearTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
            }
        }
        ThemeUtil.dealRecycleView(mBaseRv);
    }

    @Override
    public void addPresenter(List<BasePresenter> basePresenters) {
        super.addPresenter(basePresenters);
        mBaseListPresenter = new ArchiveListPresenterImpl();
    }
}
