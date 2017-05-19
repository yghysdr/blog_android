package com.shun.blog.ui.article.presenter;

import com.shun.blog.base.net.JsonCallback;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.ArticleBean;
import com.shun.blog.ui.article.contract.ArticleListContract;
import com.shun.blog.ui.article.model.ArticleListModelImpl;
import com.yghysdr.srecycleview.IFooter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleListPresenterImpl extends BasePresenter<ArticleListContract.View,
        ArticleListModelImpl> implements ArticleListContract.Presenter {

    @IFooter.Status
    int haveMore;

    @Override
    public void requestData(int type, int page, int pageSize) {
        mRxManage.addAsync(mMode
                .requestData(type, page, pageSize)
                .delaySubscription(3, TimeUnit.SECONDS)
                .compose(RxSchedulers.<BaseResponse<List<ArticleBean>>>io_main())
                .subscribe(new JsonCallback<BaseResponse<List<ArticleBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<ArticleBean>> result) {
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