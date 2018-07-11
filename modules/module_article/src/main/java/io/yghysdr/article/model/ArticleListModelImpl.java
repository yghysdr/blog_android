package io.yghysdr.article.model;

import java.util.List;

import com.github.yghysdr.http.ApiRetrofit;
import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Article;
import io.reactivex.Observable;
import io.yghysdr.article.IArticleApi;
import io.yghysdr.article.contract.ArticleListContract;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleListModelImpl implements ArticleListContract.Model {


    @Override
    public Observable<BaseResponse<List<Article>>> requestData(
            int type, int page, int pageSize) {
        return ApiRetrofit.getInstance()
                .getRetrofit()
                .create(IArticleApi.class)
                .getHomeList(type, page, pageSize);
    }

}