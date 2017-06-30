package com.shun.blog.base.ui;

import com.yghysdr.srecycleview.IFooter;

/**
 * Created by yghysdr on 2017/04/27
 */

public abstract class BaseListPresenter<V, M extends BaseModel>
        extends BasePresenter<V, M> {

    @IFooter.Status
    protected int haveMore;

    public abstract void requestData(Object msg, int page, int pageSize);


    @IFooter.Status
    public int haveMore() {
        return haveMore;
    }

}