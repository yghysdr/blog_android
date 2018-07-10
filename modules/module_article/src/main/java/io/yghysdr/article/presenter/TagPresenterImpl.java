package io.yghysdr.article.presenter;

import java.util.List;

import com.github.yghysdr.http.ApiRetrofit;
import com.github.yghysdr.http.HttpException;
import com.github.yghysdr.http.NetObserver;
import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Tag;
import com.github.yghysdr.base.ui.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.yghysdr.article.IArticleApi;
import io.yghysdr.article.contract.TagContract;

/**
 * Created by yghysdr on 2017/07/04
 */

public class TagPresenterImpl extends BasePresenter
        implements TagContract.Presenter {

    @Override
    public void requestTag(TagContract.View view) {
        ApiRetrofit.getInstance()
                .getRetrofit()
                .create(IArticleApi.class)
                .getTagData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver<BaseResponse<List<Tag>>>() {
                    @Override
                    public void onError(HttpException e) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<Tag>> listBaseResponse) {
                        view.addTags(listBaseResponse.data);
                    }
                });
    }


}