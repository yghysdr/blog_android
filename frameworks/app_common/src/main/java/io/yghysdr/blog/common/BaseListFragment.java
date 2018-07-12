package io.yghysdr.blog.common;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import cn.refactor.multistatelayout.MultiStateLayout;
import com.github.yghysdr.http.HttpException;
import com.github.yghysdr.http.NetExceptionHandler;
import io.blog.res.BaseResponse;

import com.github.yghysdr.recycleview.BaseRVAdapter;
import com.github.yghysdr.recycleview.IStatus;
import com.github.yghysdr.recycleview.RecycleViewHelper;
import com.github.yghysdr.base.BaseFragment;


/**
 */
public abstract class BaseListFragment extends BaseFragment
        implements RecycleViewHelper.Helper, ICommonListView {

    @BindView(R2.id.base_rv)
    public RecyclerView baseRv;
    @BindView(R2.id.base_root_multi)
    public MultiStateLayout baseRootMulti;
    @BindView(R2.id.base_recycle_srl)
    public SwipeRefreshLayout baseRecycleSrl;
    @BindView(R2.id.base_root_ll)
    public LinearLayout baseRootLl;

    protected RecycleViewHelper mHelper;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_recycle_base;
    }


    @Override
    protected void LazyLoad() {
        baseRootMulti.setState(MultiStateLayout.State.LOADING);
        mHelper = new RecycleViewHelper(
                mActivity,
                baseRv,
                getAdapter(),
                new LinearLayoutManager(getActivity()),
                baseRecycleSrl,
                this);
        mHelper.onRefresh();
        View networkErrorView = baseRootMulti.getNetworkErrorView();
        if (null != networkErrorView) {
            networkErrorView
                    .findViewById(R.id.status_reload)
                    .setOnClickListener(view -> mHelper.onRefresh());
        }
    }

    public abstract BaseRVAdapter getAdapter();


    @Override
    public void onListSuccess(BaseResponse response) {
        mHelper.setMoreStatus(response.haveMore ? IStatus.HAVE_MORE : IStatus.NO_MORE);
        mHelper.addDataToView((List) response.data);
        baseRootMulti.setState(MultiStateLayout.State.CONTENT);
    }

    @Override
    public void onListFailed(HttpException exception) {
        mHelper.stopRequest(true);
        baseRootMulti.setState(exception.code == NetExceptionHandler.ERROR_NO_NET ?
                MultiStateLayout.State.NETWORK_ERROR : MultiStateLayout.State.ERROR);
    }
}
