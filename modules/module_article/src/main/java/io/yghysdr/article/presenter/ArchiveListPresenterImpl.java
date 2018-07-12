package io.yghysdr.article.presenter;


import com.github.yghysdr.http.ApiRetrofit;
import com.github.yghysdr.http.HttpException;
import com.github.yghysdr.http.NetObserver;
import io.blog.res.BaseResponse;
import io.blog.res.bean.Archive;
import com.github.yghysdr.base.BasePresenter;
import io.reactivex.disposables.Disposable;
import io.yghysdr.article.IArticleApi;
import io.yghysdr.article.contract.ArchiveContract;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.yghysdr.blog.common.ICommonListView;


/**
 * Created by yghysdr on 2017/06/29
 */

public class ArchiveListPresenterImpl extends BasePresenter
        implements ArchiveContract.Presenter {


    @Override
    public void getArchiveList(ICommonListView iCommonListView, int page, int pageSize) {
        ApiRetrofit.getInstance()
                .getRetrofit()
                .create(IArticleApi.class)
                .getArchive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver<BaseResponse<List<Archive>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<Archive>> listBaseResponse) {
                        iCommonListView.onListSuccess(listBaseResponse);
                    }

                    @Override
                    public void onError(HttpException e) {
                        iCommonListView.onListFailed(e);
                    }
                });
    }
}