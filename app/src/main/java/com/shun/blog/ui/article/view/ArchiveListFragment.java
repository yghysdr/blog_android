package com.shun.blog.ui.article.view;

import com.shun.blog.base.ui.BaseListFragment;
import com.shun.blog.bean.Archive;
import com.shun.blog.ui.article.presenter.ArchiveListAdapter;
import com.shun.blog.ui.article.presenter.ArchiveListPresenterImpl;
import com.yghysdr.srecycleview.BaseRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 归档
 */
public class ArchiveListFragment extends BaseListFragment<ArchiveListPresenterImpl> {

    public ArchiveListFragment() {
        // Required empty public constructor
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

}
