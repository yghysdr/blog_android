package io.yghysdr.article.presenter;

import java.util.List;

import com.github.yghysdr.base.RxBus;
import com.github.yghysdr.http.HttpException;
import com.github.yghysdr.http.NetObserver;
import io.blog.res.BaseResponse;
import io.blog.res.bean.Article;
import io.blog.res.event.ThemeEvent;
import com.github.yghysdr.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import io.yghysdr.article.contract.ArticleListContract;
import io.yghysdr.article.model.ArticleListModelImpl;
import io.yghysdr.blog.common.ICommonListView;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleListPresenterImpl extends BasePresenter implements
        ArticleListContract.Presenter {

    @Override
    public void requestList(ICommonListView commonListView, int page, int pageSize, int articleType) {
        new ArticleListModelImpl()
                .requestData(articleType, page, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver<BaseResponse<List<Article>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<Article>> listBaseResponse) {
                        commonListView.onListSuccess(listBaseResponse);
                    }

                    @Override
                    public void onError(HttpException e) {
                        commonListView.onListFailed(e);
                    }
                });
    }


    public void addRxBus() {
        Disposable subscribe = RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Consumer<ThemeEvent>() {
                    @Override
                    public void accept(ThemeEvent themeEvent) throws Exception {
//                        mView.refreshTheme();
                    }
                });
        addSubscribe(subscribe);
    }

}