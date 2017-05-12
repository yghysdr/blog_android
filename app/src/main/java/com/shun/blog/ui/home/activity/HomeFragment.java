package com.shun.blog.ui.home.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseMvpFragment;
import com.shun.blog.bean.HomeBean;
import com.shun.blog.ui.home.contract.HomeContract;
import com.shun.blog.ui.home.model.HomeModelImpl;
import com.shun.blog.ui.home.presenter.HomeAdapter;
import com.shun.blog.ui.home.presenter.HomePresenterImpl;
import com.yghysdr.srecycleview.BaseBean;
import com.yghysdr.srecycleview.BaseRVAdapter;
import com.yghysdr.srecycleview.RecycleViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 */
public class HomeFragment extends BaseMvpFragment<HomePresenterImpl, HomeModelImpl>
        implements HomeContract.View, RecycleViewHelper.Helper {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srl)
    SwipeRefreshLayout homeSrl;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecycleViewHelper mHelper;
    private BaseRVAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void beforeReturn() {
        super.beforeReturn();
        mHelper = new RecycleViewHelper(mActivity, homeRv, new HomeAdapter(getActivity()),
                new LinearLayoutManager(getActivity()), homeSrl, this);
    }

    @Override
    public void onSuccess(List<HomeBean> beanList) {
        List<BaseBean> baseBeanList = new ArrayList<>();
        for (HomeBean bean : beanList) {
            baseBeanList.add(bean);
        }
        mHelper.addDataToView(baseBeanList);
    }

    @Override
    public void onFailed() {
        mHelper.stopRequest();
    }

    @Override
    public void requestData(int page, int size) {
        mPresenter.requestData(page, size);
    }

    @Override
    public boolean haveMoreData() {
        return mPresenter.haveMore();
    }
}
