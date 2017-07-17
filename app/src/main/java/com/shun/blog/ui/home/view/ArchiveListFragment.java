package com.shun.blog.ui.home.view;

import com.shun.blog.base.ui.BaseListFragment;
import com.shun.blog.bean.Archive;
import com.shun.blog.ui.home.contract.ArchiveContract;
import com.shun.blog.ui.home.presenter.ArchiveListAdapter;
import com.shun.blog.ui.home.presenter.ArchiveListPresenterImpl;
import com.yghysdr.srecycleview.BaseRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 归档
 */
public class ArchiveListFragment extends BaseListFragment<ArchiveListPresenterImpl>
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
            objectList.add(archive.year);
            objectList.addAll(archive.articleList);
        }
        mHelper.addDataToView(objectList);
    }

    @Override
    public void refreshTheme() {

    }
}
