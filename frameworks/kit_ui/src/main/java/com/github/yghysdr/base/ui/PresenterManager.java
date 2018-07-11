package com.github.yghysdr.base.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * MVP presenter生命周期管理
 */
public class PresenterManager {
    public List<BasePresenter> mPresenterList;

    public PresenterManager() {
        mPresenterList = new ArrayList<>();
    }

    public void attach() {
        for (BasePresenter presenter : mPresenterList) {
            presenter.attachView();
        }
    }

    public void detach() {
        if (mPresenterList != null) {
            for (BasePresenter presenter : mPresenterList) {
                if (presenter != null) {
                    presenter.detachView();
                }
            }
        }
    }
}
