package com.shun.blog.base.ui;

import java.util.ArrayList;
import java.util.List;

public class MvpWrapper {
    protected IMvpWrapper mMvpWrapper;
    protected List<BasePresenter> mPresenterList;

    public MvpWrapper(IMvpWrapper mvpWrapper) {
        mPresenterList = new ArrayList<>();
        mMvpWrapper = mvpWrapper;
    }

    protected void attach() {
        mMvpWrapper.addPresenter(mPresenterList);
        for (BasePresenter presenter : mPresenterList) {
            presenter.attachView(mMvpWrapper);
            mPresenterList.add(presenter);
        }
    }

    protected void detach() {
        if (mPresenterList != null) {
            for (BasePresenter presenter : mPresenterList) {
                if (presenter != null) {
                    presenter.detachView();
                }
            }
        }
    }

    public interface IMvpWrapper {
        void addPresenter(List<BasePresenter> basePresenters);
    }
}
