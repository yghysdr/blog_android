package com.shun.blog.ui.article.view;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.article.presenter.ArchivePresenterImpl;

/**
 * 归档
 */
public class ArchiveFragment extends BaseFragment<ArchivePresenterImpl> {

    public ArchiveFragment() {
        // Required empty public constructor
    }

    public static ArchiveFragment newInstance() {
        return new ArchiveFragment();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_archive;
    }

}
