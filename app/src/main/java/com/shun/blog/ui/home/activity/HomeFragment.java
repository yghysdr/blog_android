package com.shun.blog.ui.home.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shun.blog.R;
import com.shun.blog.base.net.Error;
import com.shun.blog.base.ui.BaseMvpFragment;
import com.shun.blog.bean.HomeBean;
import com.shun.blog.ui.home.contract.HomeContract;
import com.shun.blog.ui.home.model.HomeModelImpl;
import com.shun.blog.ui.home.presenter.HomeAdapter;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;
import com.shun.blog.weights.multistatelayout.MultiStateLayout;
import com.yghysdr.srecycleview.BaseBean;
import com.yghysdr.srecycleview.RecycleViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 */
public class HomeFragment extends BaseMvpFragment<HomePresenterImpl, HomeModelImpl>
        implements HomeContract.View, RecycleViewHelper.Helper {

    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srl)
    SwipeRefreshLayout homeSrl;
    @BindView(R.id.home_multi)
    MultiStateLayout homeMulti;

    private RecycleViewHelper mHelper;
    private boolean mHaveData;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void beforeReturn() {
        super.beforeReturn();
        homeMulti.setState(MultiStateLayout.State.LOADING);
        mHelper = new RecycleViewHelper(mActivity, homeRv, new HomeAdapter(getActivity()),
                new LinearLayoutManager(getActivity()), homeSrl, this);
        mHelper.onRefresh();
        View networkErrorView = homeMulti.getNetworkErrorView();
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


    @Override
    public void onSuccess(List<HomeBean> beanList) {
        List<BaseBean> baseBeanList = new ArrayList<>();
        for (HomeBean bean : beanList) {
            baseBeanList.add(bean);
        }
        mHelper.addDataToView(baseBeanList);
        homeMulti.setState(MultiStateLayout.State.CONTENT);
        mHaveData = true;
    }

    @Override
    public void onFailed(int errorNo, String errorMsg) {
        if (!mHaveData) {
            if (errorNo == Error.NO_NET_ERROR) {
                homeMulti.setState(MultiStateLayout.State.NETWORK_ERROR);
            } else {
                homeMulti.setState(MultiStateLayout.State.ERROR);
            }
        }
        mHelper.stopRequest();
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestData(page, size);
    }

    @Override
    public int haveMoreData() {
        return mPresenter.haveMore();
    }

}
