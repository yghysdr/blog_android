package com.shun.blog.ui.home.presenter;

import com.shun.blog.base.net.JsonCallback;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BaseListPresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Archive;
import com.shun.blog.ui.home.contract.ArchiveContract;
import com.shun.blog.ui.home.model.ArchiveModelImpl;
import com.shun.blog.ui.home.view.ArchiveListFragment;
import com.yghysdr.srecycleview.IFooter;

import java.util.List;

/**
 * Created by yghysdr on 2017/06/29
 */

public class ArchiveListPresenterImpl extends BaseListPresenter<ArchiveListFragment, ArchiveModelImpl>
        implements ArchiveContract.Presenter {

    @Override
    public void requestData(Object msg, int page, int pageSize) {
        mRxManage.addAsync(mMode
                .requestData(page, pageSize)
                .compose(RxSchedulers.<BaseResponse<List<Archive>>>io_main())
                .subscribe(new JsonCallback<BaseResponse<List<Archive>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<Archive>> result) {
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
}