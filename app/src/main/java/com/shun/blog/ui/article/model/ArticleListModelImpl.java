package com.shun.blog.ui.article.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseModel;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Article;
import com.shun.blog.ui.article.contract.ArticleListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleListModelImpl extends BaseModel
        implements ArticleListContract.Model {


    @Override
    public Observable<BaseResponse<List<Article>>> requestData(
            int type, int page, int pageSize) {
        return AppWebClient
                .getApiStores(HostType.BLOG)
                .getHomeList(type, page, pageSize);
    }

}