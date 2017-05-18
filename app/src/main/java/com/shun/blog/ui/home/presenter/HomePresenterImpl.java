package com.shun.blog.ui.home.presenter;

import com.shun.blog.base.net.JsonCallback;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.HomeBean;
import com.shun.blog.ui.home.view.HomeContract;
import com.shun.blog.ui.home.model.HomeModelImpl;
import com.yghysdr.srecycleview.IFooter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yghysdr on 2017/04/27
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View,
        HomeModelImpl> implements HomeContract.Presenter {

    @IFooter.Status
    int haveMore;

    @Override
    public void requestData(int page, int pageSize) {
        mRxManage.addAsync(mMode
                .requestData(page, pageSize)
                .delaySubscription(3, TimeUnit.SECONDS)
                .compose(RxSchedulers.<BaseResponse<List<HomeBean>>>io_main())
                .subscribe(new JsonCallback<BaseResponse<List<HomeBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<HomeBean>> result) {
                        haveMore = result.haveMore ? IFooter.HAVE_MORE : IFooter.NO_MORE;
                        mView.onSuccess(result.data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        super.onFailure(code, msg);
                        haveMore = IFooter.ERROR;
                        mView.onFailed(code, msg);
                    }
                }));
    }

    @IFooter.Status
    @Override
    public int haveMore() {
        return haveMore;
    }
}