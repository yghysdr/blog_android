package com.shun.blog.base.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.shun.blog.R;
import com.shun.blog.base.net.Error;
import com.shun.blog.weights.multistatelayout.MultiStateLayout;
import com.yghysdr.srecycleview.BaseRVAdapter;
import com.yghysdr.srecycleview.RecycleViewHelper;

import butterknife.BindView;

/**
 */
public abstract class BaseListFragment<P extends BaseListPresenter> extends BaseFragment<P>
        implements RecycleViewHelper.Helper {

    @BindView(R.id.base_rv)
    protected RecyclerView mBaseRv;
    @BindView(R.id.base_recycle_srl)
    protected SwipeRefreshLayout mBaseSrl;
    @BindView(R.id.base_root_multi)
    protected MultiStateLayout mBaseMulti;
    @BindView(R.id.base_root_ll)
    protected LinearLayout mBaseRootLl;

    protected RecycleViewHelper mHelper;
    private boolean mHaveData;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recycle_base;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mBaseMulti.setState(MultiStateLayout.State.LOADING);
        mHelper = new RecycleViewHelper(
                mActivity,
                mBaseRv,
                getAdapter(),
                new BaseFooterHolder(mActivity, null),
                new LinearLayoutManager(getActivity()),
                mBaseSrl,
                this);
        mHelper.onRefresh();
        View networkErrorView = mBaseMulti.getNetworkErrorView();
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

    public abstract BaseRVAdapter getAdapter();

    public void onSuccess(Object beanList) {
        addDateToList(beanList);
        mBaseMulti.setState(MultiStateLayout.State.CONTENT);
        mHaveData = true;
    }

    public abstract void addDateToList(Object beanList);

    public void onFailed(int errorNo, String errorMsg) {
        if (!mHaveData) {
            if (errorNo == Error.NO_NET_ERROR) {
                mBaseMulti.setState(MultiStateLayout.State.NETWORK_ERROR);
            } else {
                mBaseMulti.setState(MultiStateLayout.State.ERROR);
            }
        }
        mHelper.stopRequest();
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestData(null, page, size);
    }

    @Override
    public int haveMoreData() {
        return mPresenter.haveMore();
    }

}
