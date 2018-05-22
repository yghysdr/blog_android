package com.shun.blog.ui.article.presenter;

import com.shun.blog.base.net.JsonCallback;
import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BaseListPresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Article;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.article.contract.ArticleListContract;
import com.shun.blog.ui.article.model.ArticleListModelImpl;
import com.shun.blog.ui.article.view.ArticleListFragment;
import com.yghysdr.srecycleview.IFooter;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleListPresenterImpl extends BaseListPresenter<ArticleListFragment,
        ArticleListModelImpl> implements ArticleListContract.Presenter {

    @Override
    public void requestData(Object msg, int page, int pageSize) {
        mRxManage.addSubscription(mMode
                .requestData((Integer)msg, page, pageSize)
                .compose(RxSchedulers.<BaseResponse<List<Article>>>io_main())
                .subscribe(new JsonCallback<BaseResponse<List<Article>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<Article>> result) {
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

    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addSubscription(RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Action1<ThemeEvent>() {
                    @Override
                    public void call(ThemeEvent themeEvent) {
                        mView.refreshTheme();
                    }
                }));
    }
}